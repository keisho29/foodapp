package com.example.foodapp.service.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.foodapp.FoodappApplication;
import com.example.foodapp.dto.FoodCalcRequestDto;
import com.example.foodapp.dto.FoodCalcRequestWrapperDto;
import com.example.foodapp.model.CalcResult;
import com.example.foodapp.model.Food;
import com.example.foodapp.model.IntakeAmount;
import com.example.foodapp.model.IntakeTotalsAccumulator;
import com.example.foodapp.model.PerItemResult;
import com.example.foodapp.repository.FoodRepository;

@Service
public class FoodCalcDomainService {

	private final FoodappApplication foodappApplication;

	private final FoodRepository foodRepository;
	private final int DISPLAY_SCALE = 18;
	private final RoundingMode DISPLAY_ROUNDING = RoundingMode.HALF_UP;

	//	DIとして、インスタンスが生成されるため、コンストラクタを用意
	public FoodCalcDomainService(FoodRepository foodRepository, FoodappApplication foodappApplication) {
		this.foodRepository = foodRepository;
		this.foodappApplication = foodappApplication;

	}

	//	@実際摂取量換算メソッド
	private IntakeAmount intakeConverter(Food food, BigDecimal intake) {
		//		まずは入力チェックをする
		if (food == null) {
			throw new IllegalArgumentException("計算で使用するFoodエンティティがnullは許容されていない");
		}
		if (intake == null) {
			throw new IllegalArgumentException("計算で使用するIntakeクラス（摂取量)がnullは許容されていない");
		}
		if (intake.signum() <= 0) {
			throw new IllegalArgumentException("計算で使用するIntakeクラス(摂取量)が0以下は許容されていない");
		}
		if (food.getQuantity() == null) {
			throw new IllegalArgumentException("計算で使用するFoodエンティティの基準値がnullは許容されていない");
		}
		if (food.getQuantity().signum() <= 0) {
			throw new IllegalArgumentException("計算で使用するFoodエンティティの基準値が0以下は許容されていない");
		}
		if (food.getProtein() == null || food.getFat() == null
			    || food.getCarbo() == null || food.getCalorie() == null) {
			    throw new IllegalArgumentException("Food の栄養値に null は許容されていない");
			}

		//		除算をする際に冗長なコードにならないために、ここでまとめる
		//		摂取量/基準値（小数点以下18桁表示、四捨五入）
		BigDecimal ratio = intake.divide(food.getQuantity(), DISPLAY_SCALE, DISPLAY_ROUNDING);

		//		DBにある基準値と受け取った摂取量で実際に摂取した栄養素を計算
		BigDecimal intakeProtein = (food.getProtein()).multiply(ratio);
		BigDecimal intakeFat = (food.getFat()).multiply(ratio);
		BigDecimal intakeCarbo = (food.getCarbo()).multiply(ratio);
		BigDecimal intakeCalorie = (food.getCalorie()).multiply(ratio);

		//		結果を組み立てる
		IntakeAmount intakeAmount = new IntakeAmount(food.getId(), food.getFood_name(), intakeCalorie, intakeProtein,
				intakeFat, intakeCarbo, intake);
		return intakeAmount;
	}

	//	@桁数調整メソッド
	private BigDecimal digitAdjustment(BigDecimal bigDecimal) {

		//		nullチェック
		//		値がnullであれば0として返す
		if (bigDecimal == null) {
			return BigDecimal.ZERO;
		}

		//		実際に摂取した栄養素を小数点第２を四捨五入
		//		小数点第１まで表示
		return bigDecimal.setScale(1, DISPLAY_ROUNDING);
	}

	//	@メインメソッド
	//	引数として渡されるのは食材と摂取量のデータをリストとして持つオブジェクト
	public CalcResult foodCalc(FoodCalcRequestWrapperDto req) {
		//		入力ガード
		//		リクエストボディが空、もしくはfood_itemが存在しないとき、空のリストを返す。
		//		存在するとき、渡されたDTOの中のリストを代入(rowsにはFoodCalcRequestWrapperDtoのリストがそのまま代入される(同じリストを参照するようになる)）
		List<FoodCalcRequestDto> rows = (req == null || req.getFoodItems() == null) ? List.of() : req.getFoodItems();

		//		上記で作成されたリストの中身が空であるなら栄養素は0、食材明細は空で返す
		if (rows.isEmpty()) {
			return new CalcResult(digitAdjustment(BigDecimal.ZERO),
					digitAdjustment(BigDecimal.ZERO),
					digitAdjustment(BigDecimal.ZERO),
					digitAdjustment(BigDecimal.ZERO),
					new ArrayList<>());
		}
		//	引数から渡された摂取食材をDBから取得し、実際の摂取栄養素を換算

		//	摂取栄養素換算結果を詰めるリストを定義しておく
		List<IntakeAmount> intakeAmounts = new ArrayList<>();

		//	合計値を詰めるインスタンスを生成しておく
		IntakeTotalsAccumulator intakeTotalsAccumulator = new IntakeTotalsAccumulator();

		//	rowsから１件ずつ食材を取得し、DB取得から実際の栄養素換算まで行う
		for (FoodCalcRequestDto row : rows) {

			//		１件ずつIDを取得
			Long id = row.getId();

			//		取得したIDでDB検索
			Optional<Food> optionalFood = foodRepository.findById(id);

			//		DB検索の結果、見つかればその食材データを返し、見つからなければエラーを返す
			Food food = optionalFood.orElseThrow(
					() -> new IllegalArgumentException("食材が見つかりません：ID＝" + id));

			//		引数から渡された摂取量を使用し、実際の栄養素を換算（ヘルパー呼び出し）
			IntakeAmount intakeConverterResults = intakeConverter(food, row.getIntake());

			//		実際栄養素換算ヘルパーで計算後、リストにつめる（換算した状態であり、桁数調整はしていない）
			intakeAmounts.add(intakeConverterResults);

			//		合計を計算し、格納（桁数調整前）
			intakeTotalsAccumulator.addCalorie(intakeConverterResults.getCalorie());
			intakeTotalsAccumulator.addCarbo(intakeConverterResults.getCarbo());
			intakeTotalsAccumulator.addFat(intakeConverterResults.getFat());
			intakeTotalsAccumulator.addProtein(intakeConverterResults.getProtein());

		}

		//		CalcResultの組み立て

		//		一食分の明細リストを作成。桁数調整して詰める
		List<PerItemResult> perItemResults = new ArrayList<>();

		//		一食分の明細データ桁数調整（ヘルパー呼び出し）
		for (IntakeAmount item : intakeAmounts) {
			PerItemResult perItemResult = new PerItemResult(item.getId(), item.getFood_name(),
					digitAdjustment(item.getCalorie()), digitAdjustment(item.getProtein()),
					digitAdjustment(item.getFat()), digitAdjustment(item.getCarbo()), item.getIntake());
			perItemResults.add(perItemResult);

		}

		//		合計値データ桁数調整（ヘルパー呼び出し）し、CalcResult組み立て
		CalcResult calcResult = new CalcResult(digitAdjustment(intakeTotalsAccumulator.getTotal_calorie()),
				digitAdjustment(intakeTotalsAccumulator.getTotal_protein()),
				digitAdjustment(intakeTotalsAccumulator.getTotal_fat()),
				digitAdjustment(intakeTotalsAccumulator.getTotal_carbo()), perItemResults);

		//		結果を返す
		return calcResult;
	}
}
