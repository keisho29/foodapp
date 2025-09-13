package com.example.foodapp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class CalcResultTest {

    @Test
    void コンストラクタで渡した値が取得できる() {
        // Arrange
        BigDecimal calorie = BigDecimal.valueOf(500);
        BigDecimal protein = BigDecimal.valueOf(30);
        BigDecimal fat = BigDecimal.valueOf(20);
        BigDecimal carbo = BigDecimal.valueOf(60);

        PerItemResult perItem = new PerItemResult(
                1L,
                "鶏むね肉",
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(30),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(100)
        );
        List<PerItemResult> perItemResults = List.of(perItem);

        // Act
        CalcResult result = new CalcResult(calorie, protein, fat, carbo, perItemResults);

        // Assert
        assertEquals(calorie, result.getTotal_calorie());
        assertEquals(protein, result.getTotal_protein());
        assertEquals(fat, result.getTotal_fat());
        assertEquals(carbo, result.getTotal_carbo());
        assertEquals(perItemResults, result.getPerItemResults());
    }

    @Test
    void perItemResultsが空リストでも保持される() {
        BigDecimal calorie = BigDecimal.ZERO;
        BigDecimal protein = BigDecimal.ZERO;
        BigDecimal fat = BigDecimal.ZERO;
        BigDecimal carbo = BigDecimal.ZERO;

        List<PerItemResult> emptyList = Collections.emptyList();

        CalcResult result = new CalcResult(calorie, protein, fat, carbo, emptyList);

        assertEquals(emptyList, result.getPerItemResults());
        assertTrue(result.getPerItemResults().isEmpty());
    }
}
