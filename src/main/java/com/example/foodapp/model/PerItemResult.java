package com.example.foodapp.model;

import java.math.BigDecimal;

//ドメインサービスの計算によって算出される１食分の明細を保持する
//ドメインサービスのメインメソッドの中で組み立てられる
public class PerItemResult {

	private final Long id;
	private final String food_name;
	private final BigDecimal calorie;
	private final BigDecimal protein;
	private final BigDecimal fat;
	private final BigDecimal carbo;
	private final BigDecimal intake;
	
	public PerItemResult(Long id, String food_name, BigDecimal calorie, BigDecimal protein, BigDecimal fat,
			BigDecimal carbo, BigDecimal intake) {
		this.id = id;
		this.food_name = food_name;
		this.calorie = calorie;
		this.protein = protein;
		this.fat = fat;
		this.carbo = carbo;
		this.intake= intake;
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
