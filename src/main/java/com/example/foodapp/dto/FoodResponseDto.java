package com.example.foodapp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//表示用DTO（登録・更新）
public class FoodResponseDto {

	private Long id;
	private String food_name;
	private BigDecimal calorie;
	private BigDecimal protein;
	private BigDecimal fat;
	private BigDecimal carbo;
	private LocalDateTime create_date;
	private BigDecimal quantity;
	
	public FoodResponseDto() {};

	public FoodResponseDto(Long id,String food_name, BigDecimal calorie, BigDecimal protein, BigDecimal fat, BigDecimal carbo,
			LocalDateTime create_date, BigDecimal quantity) {

		this.id=id;
		this.food_name = food_name;
		this.calorie = calorie;
		this.protein = protein;
		this.fat = fat;
		this.carbo = carbo;
		this.create_date = create_date;
		this.quantity = quantity;
	}
	
	

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

	public LocalDateTime getCreate_date() {
		return create_date;
	}

	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
