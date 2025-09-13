package com.example.foodapp.dto;

import java.util.List;

import jakarta.validation.Valid;

public class FoodCalcRequestWrapperDto {

//	ドメインサービスに渡すデータを定義するクラス
//	ここで定義した属性がドメインサービス内で参照することができる
	
	@Valid
	private List<FoodCalcRequestDto> food_items;

	public FoodCalcRequestWrapperDto() {
		// Jacksonがインスタンスを作成するため空でOK
	}

	public List<FoodCalcRequestDto> getFoodItems() {
		return food_items;
	}

	public void setFoodItems(List<FoodCalcRequestDto> food_items) {
		this.food_items = food_items;
	}
	
	
}
