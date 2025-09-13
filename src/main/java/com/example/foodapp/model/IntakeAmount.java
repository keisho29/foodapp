package com.example.foodapp.model;

import java.math.BigDecimal;

//ドメインサービス内で使用されるヘルパーの計算結果受け取り用オブジェクト
//実際に摂取した量を計算した結果を保持する

public class IntakeAmount {

	private final Long id;
	private final String food_name;
	private final BigDecimal calorie;
	private final BigDecimal protein;
	private final BigDecimal fat;
	private final BigDecimal carbo;
	private final BigDecimal intake;

	public IntakeAmount(Long id, String food_name, BigDecimal calorie, BigDecimal protein, BigDecimal fat,
			BigDecimal carbo, BigDecimal intake) {
		this.id = id;
		this.food_name = food_name;
		this.calorie = calorie;
		this.protein = protein;
		this.fat = fat;
		this.carbo = carbo;
		this.intake = intake;
	}

	public Long getId() {
		return id;
	}

	public String getFood_name() {
		return food_name;
	}

	public BigDecimal getCalorie() {
		return calorie;
	}

	public BigDecimal getProtein() {
		return protein;
	}

	public BigDecimal getFat() {
		return fat;
	}

	public BigDecimal getCarbo() {
		return carbo;
	}
	public BigDecimal getIntake() {
		return intake;
	}

}
