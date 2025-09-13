package com.example.foodapp.mapper;

import com.example.foodapp.dto.FoodRequestDto;
import com.example.foodapp.dto.FoodResponseDto;
import com.example.foodapp.model.Food;

public class FoodMapper {

	//	FoodRequestDtoからエンティティクラスへの変換
	public static Food convertToEntity(FoodRequestDto foodRequestDto) {
		Food food = new Food();
		food.setId(foodRequestDto.getId());
		food.setFood_name(foodRequestDto.getFood_name());
		food.setCalorie(foodRequestDto.getCalorie());
		food.setProtein(foodRequestDto.getProtein());
		food.setFat(foodRequestDto.getFat());
		food.setCarbo(foodRequestDto.getCarbo());
		food.setQuantity(foodRequestDto.getQuantity());		
		return food;

//		引数アリコンストラクタを定義しているため、下記のように記載することもできる
//		return new Food(foodRequestDto.getId(),foodRequestDto.getFood_name(),foodRequestDto.getCalorie(),foodRequestDto.getProtein(),foodRequestDto.getFat(),foodRequestDto.getCarbo(),foodRequestDto.getQuantity());
	}

	//	エンティティクラスからFoodResponseDtoへの変換
	public static FoodResponseDto convertToFoodResponseDto(Food food) {
		FoodResponseDto foodResponseDto = new FoodResponseDto();
		foodResponseDto.setId(food.getId());
		foodResponseDto.setFood_name(food.getFood_name());
		foodResponseDto.setCalorie(food.getCalorie());
		foodResponseDto.setProtein(food.getProtein());
		foodResponseDto.setFat(food.getFat());
		foodResponseDto.setCarbo(food.getCarbo());
		foodResponseDto.setCreate_date(food.getCreate_date());
		foodResponseDto.setQuantity(food.getQuantity());
		return foodResponseDto;

	}

}
