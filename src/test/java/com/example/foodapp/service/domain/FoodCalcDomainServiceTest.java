package com.example.foodapp.service.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.foodapp.FoodappApplication;
import com.example.foodapp.dto.FoodCalcRequestDto;
import com.example.foodapp.dto.FoodCalcRequestWrapperDto;
import com.example.foodapp.model.CalcResult;
import com.example.foodapp.model.Food;
import com.example.foodapp.repository.FoodRepository;

class FoodCalcDomainServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private FoodappApplication foodappApplication;

    @InjectMocks
    private FoodCalcDomainService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Food createFood(Long id) {
        Food food = new Food();
        food.setId(id);
        food.setFood_name("鶏むね肉");
        food.setCalorie(BigDecimal.valueOf(200));
        food.setProtein(BigDecimal.valueOf(30));
        food.setFat(BigDecimal.valueOf(5));
        food.setCarbo(BigDecimal.valueOf(0));
        food.setQuantity(BigDecimal.valueOf(100));
        return food;
    }

    @Test
    void 正常に計算できる() {
        // Arrange
        Long id = 1L;
        Food food = createFood(id);

        when(foodRepository.findById(id)).thenReturn(Optional.of(food));

        FoodCalcRequestDto dto = new FoodCalcRequestDto();
        dto.setId(id);
        dto.setIntake(BigDecimal.valueOf(200)); // 基準100に対して2倍

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(List.of(dto));

        // Act
        CalcResult result = service.foodCalc(wrapper);

        // Assert
        assertEquals(BigDecimal.valueOf(400.0).setScale(1), result.getTotal_calorie());
        assertEquals(BigDecimal.valueOf(60.0).setScale(1), result.getTotal_protein());
        assertEquals(BigDecimal.valueOf(10.0).setScale(1), result.getTotal_fat());
        assertEquals(BigDecimal.valueOf(0.0).setScale(1), result.getTotal_carbo());
        assertEquals(1, result.getPerItemResults().size());
    }

    @Test
    void 食材が存在しない場合は例外() {
        Long id = 2L;
        when(foodRepository.findById(id)).thenReturn(Optional.empty());

        FoodCalcRequestDto dto = new FoodCalcRequestDto();
        dto.setId(id);
        dto.setIntake(BigDecimal.valueOf(100));

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(List.of(dto));

        assertThrows(IllegalArgumentException.class, () -> service.foodCalc(wrapper));
    }

    @Test
    void intakeがゼロ以下なら例外() {
        Long id = 3L;
        Food food = createFood(id);
        when(foodRepository.findById(id)).thenReturn(Optional.of(food));

        FoodCalcRequestDto dto = new FoodCalcRequestDto();
        dto.setId(id);
        dto.setIntake(BigDecimal.ZERO); // ゼロ

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(List.of(dto));

        assertThrows(IllegalArgumentException.class, () -> service.foodCalc(wrapper));
    }

    @Test
    void Foodのquantityがゼロなら例外() {
        Long id = 4L;
        Food food = createFood(id);
        food.setQuantity(BigDecimal.ZERO);
        when(foodRepository.findById(id)).thenReturn(Optional.of(food));

        FoodCalcRequestDto dto = new FoodCalcRequestDto();
        dto.setId(id);
        dto.setIntake(BigDecimal.valueOf(50));

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(List.of(dto));

        assertThrows(IllegalArgumentException.class, () -> service.foodCalc(wrapper));
    }
}
