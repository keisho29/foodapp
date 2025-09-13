package com.example.foodapp.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

//入力用DTO（登録・更新）
public class FoodRequestDto {

	private Long id;

	@NotBlank(message = "食材の名前は必須です")
	//	null,空文字,空白だけの文字禁止
	private String food_name;

	@NotNull(message = "カロリーが未入力です")
	//	null禁止
	@PositiveOrZero(message = "カロリーが不正値です（0以上の値)")
	//	0以上の値
	private BigDecimal calorie;

	@NotNull(message = "タンパク質量が未入力です")
	//	null禁止
	@PositiveOrZero(message = "タンパク質量が不正値です（0以上の値)")
	//	0以上の値
	private BigDecimal protein;

	@NotNull(message = "脂質量が未入力です")
	//	null禁止
	@PositiveOrZero(message = "脂質量が不正値です（0以上の値)")
	//	0以上の値
	private BigDecimal fat;

	@NotNull(message = "炭水化物量が未入力です")
	//	null禁止
	@PositiveOrZero(message = "炭水化物量が不正値です（0以上の値)")
	//	0以上の値
	private BigDecimal carbo;

	@NotNull(message = "基準値が未入力です")
	//	null禁止
	@Positive(message = "基準値が不正値です（1以上の値)")
	//	0以上の値
	private BigDecimal quantity;

	//	デフォルトコンストラクタ
	public FoodRequestDto() {
	};

	//	変換メソッドの処理を簡潔に記載するためのコンストラクタ
	public FoodRequestDto(Long id, String food_name, BigDecimal calorie, BigDecimal protein, BigDecimal fat,
			BigDecimal carbo, BigDecimal quantity) {
		this.id = id;
		this.food_name = food_name;
		this.calorie = calorie;
		this.protein = protein;
		this.fat = fat;
		this.carbo = carbo;
		this.quantity = quantity;
	}

	//	ゲッターセッター
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public BigDecimal getCalorie() {
		return calorie;
	}

	public void setCalorie(BigDecimal calorie) {
		this.calorie = calorie;
	}

	public BigDecimal getProtein() {
		return protein;
	}

	public void setProtein(BigDecimal protein) {
		this.protein = protein;
	}

	public BigDecimal getFat() {
		return fat;
	}

	public void setFat(BigDecimal fat) {
		this.fat = fat;
	}

	public BigDecimal getCarbo() {
		return carbo;
	}

	public void setCarbo(BigDecimal carbo) {
		this.carbo = carbo;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
