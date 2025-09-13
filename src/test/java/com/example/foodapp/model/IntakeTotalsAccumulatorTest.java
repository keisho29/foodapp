package com.example.foodapp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class IntakeTotalsAccumulatorTest {
	@Test
	void インスタンス生成時に各フィールドの初期値が0である() {
		IntakeTotalsAccumulator acc = new IntakeTotalsAccumulator();

		assertEquals(BigDecimal.ZERO, acc.getTotal_calorie());
		assertEquals(BigDecimal.ZERO, acc.getTotal_protein());
		assertEquals(BigDecimal.ZERO, acc.getTotal_fat());
		assertEquals(BigDecimal.ZERO, acc.getTotal_carbo());
	}

	@Test
	void カロリーを加算できる() {
		IntakeTotalsAccumulator acc = new IntakeTotalsAccumulator();

		acc.addCalorie(BigDecimal.valueOf(100));

		assertEquals(BigDecimal.valueOf(100), acc.getTotal_calorie());
	}

	@Test
	void カロリーを複数回加算できる() {
		IntakeTotalsAccumulator acc = new IntakeTotalsAccumulator();

		acc.addCalorie(BigDecimal.valueOf(100));
		acc.addCalorie(BigDecimal.valueOf(200));

		assertEquals(BigDecimal.valueOf(300), acc.getTotal_calorie());
	}

	@Test
	void たんぱく質を加算できる() {
		IntakeTotalsAccumulator acc = new IntakeTotalsAccumulator();

		acc.addProtein(BigDecimal.valueOf(50));

		assertEquals(BigDecimal.valueOf(50), acc.getTotal_protein());
	}
	
	@Test
	void たんぱく質を複数回加算できる() {
		IntakeTotalsAccumulator acc = new IntakeTotalsAccumulator();

		acc.addProtein(BigDecimal.valueOf(50));
		acc.addProtein(BigDecimal.valueOf(150));

		assertEquals(BigDecimal.valueOf(200), acc.getTotal_protein());
	}


	@Test
	void 脂質を加算できる() {
		IntakeTotalsAccumulator acc = new IntakeTotalsAccumulator();

		acc.addFat(BigDecimal.valueOf(20));

		assertEquals(BigDecimal.valueOf(20), acc.getTotal_fat());
	}
	
	@Test
	void 脂質を複数回加算できる() {
		IntakeTotalsAccumulator acc = new IntakeTotalsAccumulator();

		acc.addFat(BigDecimal.valueOf(20));
		acc.addFat(BigDecimal.valueOf(60));

		assertEquals(BigDecimal.valueOf(80), acc.getTotal_fat());
	}

	@Test
	void 炭水化物を加算できる() {
		IntakeTotalsAccumulator acc = new IntakeTotalsAccumulator();

		acc.addCarbo(BigDecimal.valueOf(70));

		assertEquals(BigDecimal.valueOf(70), acc.getTotal_carbo());
	}
	@Test
	void 炭水化物を複数回加算できる() {
		IntakeTotalsAccumulator acc = new IntakeTotalsAccumulator();

		acc.addCarbo(BigDecimal.valueOf(70));
		acc.addCarbo(BigDecimal.valueOf(70));

		assertEquals(BigDecimal.valueOf(140), acc.getTotal_carbo());
	}

}
