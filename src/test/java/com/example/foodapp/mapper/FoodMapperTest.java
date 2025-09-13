package com.example.foodapp.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.foodapp.dto.FoodRequestDto;
import com.example.foodapp.dto.FoodResponseDto;
import com.example.foodapp.model.Food;

class FoodMapperTest {

    @Test
    void FoodRequestDtoからFoodに変換できる() {
        // Arrange
        FoodRequestDto dto = new FoodRequestDto();
        dto.setId(1L);
        dto.setFood_name("鶏むね肉");
        dto.setCalorie(BigDecimal.valueOf(200));
        dto.setProtein(BigDecimal.valueOf(30));
        dto.setFat(BigDecimal.valueOf(5));
        dto.setCarbo(BigDecimal.valueOf(0));
        dto.setQuantity(BigDecimal.valueOf(100));

        // Act
        Food food = FoodMapper.convertToEntity(dto);

        // Assert
        assertEquals(dto.getId(), food.getId());
        assertEquals(dto.getFood_name(), food.getFood_name());
        assertEquals(dto.getCalorie(), food.getCalorie());
        assertEquals(dto.getProtein(), food.getProtein());
        assertEquals(dto.getFat(), food.getFat());
        assertEquals(dto.getCarbo(), food.getCarbo());
        assertEquals(dto.getQuantity(), food.getQuantity());
    }

    @Test
    void FoodからFoodResponseDtoに変換できる() {
        // Arrange
        Food food = new Food();
        food.setId(2L);
        food.setFood_name("さつまいも");
        food.setCalorie(BigDecimal.valueOf(300));
        food.setProtein(BigDecimal.valueOf(5));
        food.setFat(BigDecimal.valueOf(1));
        food.setCarbo(BigDecimal.valueOf(70));
        food.setQuantity(BigDecimal.valueOf(150));
        LocalDateTime now = LocalDateTime.now();
        // create_date は setter がないのでリフレクションで直接セットしてもよいですが、
        // ここでは null のまま扱うことにします。

        // Act
        FoodResponseDto dto = FoodMapper.convertToFoodResponseDto(food);

        // Assert
        assertEquals(food.getId(), dto.getId());
        assertEquals(food.getFood_name(), dto.getFood_name());
        assertEquals(food.getCalorie(), dto.getCalorie());
        assertEquals(food.getProtein(), dto.getProtein());
        assertEquals(food.getFat(), dto.getFat());
        assertEquals(food.getCarbo(), dto.getCarbo());
        assertEquals(food.getQuantity(), dto.getQuantity());
        assertEquals(food.getCreate_date(), dto.getCreate_date()); // null のままでも一致
    }
}
