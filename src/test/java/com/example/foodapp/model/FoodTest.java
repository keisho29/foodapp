package com.example.foodapp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class FoodTest {

    @Test
    void ゲッターセッターで値を保持できる() {
        Food food = new Food();

        Long id = 1L;
        String foodName = "さつまいも";
        BigDecimal calorie = BigDecimal.valueOf(300);
        BigDecimal protein = BigDecimal.valueOf(5);
        BigDecimal fat = BigDecimal.valueOf(1);
        BigDecimal carbo = BigDecimal.valueOf(70);
        BigDecimal quantity = BigDecimal.valueOf(100);
        LocalDateTime now = LocalDateTime.now();

        food.setId(id);
        food.setFood_name(foodName);
        food.setCalorie(calorie);
        food.setProtein(protein);
        food.setFat(fat);
        food.setCarbo(carbo);
        food.setQuantity(quantity);

        assertEquals(id, food.getId());
        assertEquals(foodName, food.getFood_name());
        assertEquals(calorie, food.getCalorie());
        assertEquals(protein, food.getProtein());
        assertEquals(fat, food.getFat());
        assertEquals(carbo, food.getCarbo());
        assertEquals(quantity, food.getQuantity());

        // create_date は @CreatedDate 管理なので setter が無いが、null 初期値を確認
        assertNull(food.getCreate_date());
    }
}
