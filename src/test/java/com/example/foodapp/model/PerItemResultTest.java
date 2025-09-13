package com.example.foodapp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PerItemResultTest {

	@Test
	void コンストラクタで渡した値が取得できる() {
		// コンストラクタに渡す引数定義
		Long id = 1L;
		String foodName = "鶏むね肉";
		BigDecimal calorie = BigDecimal.valueOf(200);
		BigDecimal protein = BigDecimal.valueOf(30);
		BigDecimal fat = BigDecimal.valueOf(5);
		BigDecimal carbo = BigDecimal.valueOf(0);
		BigDecimal intake = BigDecimal.valueOf(150);

		// コンストラクタ呼び出し
		PerItemResult result = new PerItemResult(id, foodName, calorie, protein, fat, carbo, intake);

		// 比較テスト
		assertEquals(id, result.getId());
		assertEquals(foodName, result.getFood_name());
		assertEquals(calorie, result.getCalorie());
		assertEquals(protein, result.getProtein());
		assertEquals(fat, result.getFat());
		assertEquals(carbo, result.getCarbo());
		assertEquals(intake, result.getIntake());
	}

	@Test
	void ゼロや空文字でも保持される() {
		//    	コンストラクタに渡す引数定義
		Long id = 2L;
		String foodName = "";
		BigDecimal calorie = BigDecimal.ZERO;
		BigDecimal protein = BigDecimal.ZERO;
		BigDecimal fat = BigDecimal.ZERO;
		BigDecimal carbo = BigDecimal.ZERO;
		BigDecimal intake = BigDecimal.ZERO;

		//        コンストラクタ呼び出し
		PerItemResult result = new PerItemResult(id, foodName, calorie, protein, fat, carbo, intake);

		//        比較テスト
		assertEquals(id, result.getId());
		assertEquals(foodName, result.getFood_name());
		assertEquals(calorie, result.getCalorie());
		assertEquals(protein, result.getProtein());
		assertEquals(fat, result.getFat());
		assertEquals(carbo, result.getCarbo());
		assertEquals(intake, result.getIntake());
	}
}
