package com.example.foodapp.model;

import java.math.BigDecimal;

//ドメインサービスのメソッドに渡す引数をまとめたValueObject
public class CalcFood {

	private Food food;
	private BigDecimal intake;
	
	public CalcFood(Food food, BigDecimal intake) {
		this.food = food;
		this.intake = intake;
	}

	public Food getFood() {
		return food;
	}

	public BigDecimal getIntake() {
		return intake;
	}
	
	
	
	
}
