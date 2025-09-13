package com.example.foodapp.service.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.foodapp.FoodappApplication;
import com.example.foodapp.dto.FoodCalcRequestDto;
import com.example.foodapp.dto.FoodCalcRequestWrapperDto;
import com.example.foodapp.model.CalcResult;
import com.example.foodapp.model.Food;
import com.example.foodapp.model.PerItemResult;
import com.example.foodapp.repository.FoodRepository;

/**
 * FoodCalcDomainService の複数食材計算に関する単体テスト
 */
@ExtendWith(MockitoExtension.class)
class FoodCalcDomainServiceMultiItemsTest {

    @Mock
    private FoodRepository foodRepository; // DBアクセスをモック化

    @Mock
    private FoodappApplication foodappApplication; // DIで要求されるが今回のテストでは未使用

    @InjectMocks
    private FoodCalcDomainService service; // テスト対象クラス（モックを注入）

    /**
     * テスト用の Food エンティティを生成する補助メソッド
     */
    private Food food(Long id, String name,
                      double cal, double pro, double fat, double carbo,
                      double quantity) {
        Food f = new Food();
        f.setId(id);
        f.setFood_name(name);
        f.setCalorie(BigDecimal.valueOf(cal));
        f.setProtein(BigDecimal.valueOf(pro));
        f.setFat(BigDecimal.valueOf(fat));
        f.setCarbo(BigDecimal.valueOf(carbo));
        f.setQuantity(BigDecimal.valueOf(quantity));
        return f;
    }

    @Test
    void 複数食材を計算して合計と明細を返す() {
        // --- テストデータ準備 ---
        // Food#1: 基準100g、摂取150g → 1.5倍になる想定
        Food chicken = food(1L, "鶏むね肉", 200, 30, 5, 0, 100);
        // Food#2: 基準50g、摂取25g → 0.5倍になる想定
        Food sweetPotato = food(2L, "さつまいも", 80, 3, 1, 17, 50);

        // --- リポジトリのモック設定 ---
        when(foodRepository.findById(1L)).thenReturn(Optional.of(chicken));
        when(foodRepository.findById(2L)).thenReturn(Optional.of(sweetPotato));

        // --- 入力DTOを作成（2件分） ---
        FoodCalcRequestDto row1 = new FoodCalcRequestDto();
        row1.setId(1L);
        row1.setIntake(BigDecimal.valueOf(150)); // 鶏むね肉 150g

        FoodCalcRequestDto row2 = new FoodCalcRequestDto();
        row2.setId(2L);
        row2.setIntake(BigDecimal.valueOf(25)); // さつまいも 25g

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(List.of(row1, row2));

        // --- サービス実行 ---
        CalcResult result = service.foodCalc(wrapper);

        // --- 期待値計算（小数第1位まで四捨五入） ---
        // 鶏むね肉: cal 300.0, pro 45.0, fat 7.5, carbo 0.0
        // さつまいも: cal 40.0, pro 1.5, fat 0.5, carbo 8.5
        BigDecimal CAL_TOT = BigDecimal.valueOf(340.0).setScale(1);
        BigDecimal PRO_TOT = BigDecimal.valueOf(46.5).setScale(1);
        BigDecimal FAT_TOT = BigDecimal.valueOf(8.0).setScale(1);
        BigDecimal CAR_TOT = BigDecimal.valueOf(8.5).setScale(1);

        // --- 合計値の検証 ---
        assertAll("合計の検証（小数1桁・HALF_UP）",
            () -> assertEquals(CAL_TOT, result.getTotal_calorie()),
            () -> assertEquals(PRO_TOT, result.getTotal_protein()),
            () -> assertEquals(FAT_TOT, result.getTotal_fat()),
            () -> assertEquals(CAR_TOT, result.getTotal_carbo())
        );

        // --- 明細リストの検証 ---
        List<PerItemResult> items = result.getPerItemResults();
        assertEquals(2, items.size(), "明細件数");

        // 1件目（鶏むね肉）の明細検証
        PerItemResult i1 = items.get(0);
        assertAll("1件目（鶏むね肉）",
            () -> assertEquals(1L, i1.getId()),
            () -> assertEquals("鶏むね肉", i1.getFood_name()),
            () -> assertEquals(BigDecimal.valueOf(300.0).setScale(1), i1.getCalorie()),
            () -> assertEquals(BigDecimal.valueOf(45.0).setScale(1), i1.getProtein()),
            () -> assertEquals(BigDecimal.valueOf(7.5).setScale(1),  i1.getFat()),
            () -> assertEquals(BigDecimal.valueOf(0.0).setScale(1),  i1.getCarbo()),
            () -> assertEquals(BigDecimal.valueOf(150), i1.getIntake()) // intakeは丸めなし
        );

        // 2件目（さつまいも）の明細検証
        PerItemResult i2 = items.get(1);
        assertAll("2件目（さつまいも）",
            () -> assertEquals(2L, i2.getId()),
            () -> assertEquals("さつまいも", i2.getFood_name()),
            () -> assertEquals(BigDecimal.valueOf(40.0).setScale(1), i2.getCalorie()),
            () -> assertEquals(BigDecimal.valueOf(1.5).setScale(1),  i2.getProtein()),
            () -> assertEquals(BigDecimal.valueOf(0.5).setScale(1),  i2.getFat()),
            () -> assertEquals(BigDecimal.valueOf(8.5).setScale(1),  i2.getCarbo()),
            () -> assertEquals(BigDecimal.valueOf(25), i2.getIntake())
        );
    }
}
