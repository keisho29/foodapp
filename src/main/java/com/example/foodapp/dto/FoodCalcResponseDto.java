package com.example.foodapp.dto;

import java.math.BigDecimal;

public class FoodCalcResponseDto {

	private BigDecimal total_calorie;
	private BigDecimal total_protein;
	private BigDecimal total_fat;
	private BigDecimal total_carbo;
	
	

	public FoodCalcResponseDto() {};

	public FoodCalcResponseDto(BigDecimal total_calorie, BigDecimal total_protein, BigDecimal total_fat,
			BigDecimal total_carbo) {
		this.total_calorie = total_calorie;
		this.total_protein = total_protein;
		this.total_fat = total_fat;
		this.total_carbo = total_carbo;
	}

	public BigDecimal getTotal_calorie() {
		return total_calorie;
	}

	public void setTotal_calorie(BigDecimal total_calorie) {
		this.total_calorie = total_calorie;
	}

	public BigDecimal getTotal_protein() {
		return total_protein;
	}

	public void setTotal_protein(BigDecimal total_protein) {
		this.total_protein = total_protein;
	}

	public BigDecimal getTotal_fat() {
		return total_fat;
	}

	public void setTotal_fat(BigDecimal total_fat) {
		this.total_fat = total_fat;
	}

	public BigDecimal getTotal_carbo() {
		return total_carbo;
	}

	public void setTotal_carbo(BigDecimal total_carbo) {
		this.total_carbo = total_carbo;
	}

}
