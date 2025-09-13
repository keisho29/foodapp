package com.example.foodapp.service.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.foodapp.FoodappApplication;
import com.example.foodapp.dto.FoodCalcRequestDto;
import com.example.foodapp.dto.FoodCalcRequestWrapperDto;
import com.example.foodapp.model.Food;
import com.example.foodapp.repository.FoodRepository;

/**
 * FoodCalcDomainService の例外系テスト
 */
@ExtendWith(MockitoExtension.class)
class FoodCalcDomainServiceExceptionTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private FoodappApplication foodappApplication;

    @InjectMocks
    private FoodCalcDomainService service;

    /**
     * DB に存在しない ID を指定した場合 → IllegalArgumentException
     */
    @Test
    void 存在しないIDを指定すると例外が投げられる() {
        // モック設定（空を返す）
        when(foodRepository.findById(99L)).thenReturn(Optional.empty());

        // 入力準備
        FoodCalcRequestDto row = new FoodCalcRequestDto();
        row.setId(99L);
        row.setIntake(BigDecimal.valueOf(100));

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(java.util.List.of(row));

        // 実行 & 検証
        assertThrows(IllegalArgumentException.class, () -> service.foodCalc(wrapper));
    }

    /**
     * 摂取量が null の場合 → IllegalArgumentException
     */
    @Test
    void 摂取量がnullなら例外が投げられる() {
        Food food = validFood();
        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));

        FoodCalcRequestDto row = new FoodCalcRequestDto();
        row.setId(1L);
        row.setIntake(null); // intake が null

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(java.util.List.of(row));

        assertThrows(IllegalArgumentException.class, () -> service.foodCalc(wrapper));
    }

    /**
     * 摂取量が0以下の場合 → IllegalArgumentException
     */
    @Test
    void 摂取量が0以下なら例外が投げられる() {
        Food food = validFood();
        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));

        FoodCalcRequestDto row = new FoodCalcRequestDto();
        row.setId(1L);
        row.setIntake(BigDecimal.ZERO); // intake = 0

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(java.util.List.of(row));

        assertThrows(IllegalArgumentException.class, () -> service.foodCalc(wrapper));
    }

    /**
     * Food の基準量(quantity)が null の場合 → IllegalArgumentException
     */
    @Test
    void Foodの基準量がnullなら例外が投げられる() {
        Food food = validFood();
        food.setQuantity(null); // quantity が null
        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));

        FoodCalcRequestDto row = new FoodCalcRequestDto();
        row.setId(1L);
        row.setIntake(BigDecimal.valueOf(50));

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(java.util.List.of(row));

        assertThrows(IllegalArgumentException.class, () -> service.foodCalc(wrapper));
    }

    /**
     * Food の栄養値(calorieなど)に null が含まれる場合 → IllegalArgumentException
     */
    @Test
    void Foodの栄養値がnullなら例外が投げられる() {
        Food food = validFood();
        food.setProtein(null); // protein を null にする
        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));

        FoodCalcRequestDto row = new FoodCalcRequestDto();
        row.setId(1L);
        row.setIntake(BigDecimal.valueOf(100));

        FoodCalcRequestWrapperDto wrapper = new FoodCalcRequestWrapperDto();
        wrapper.setFoodItems(java.util.List.of(row));

        assertThrows(IllegalArgumentException.class, () -> service.foodCalc(wrapper));
    }

    // --- テスト用の有効な Food を返す補助メソッド ---
    private Food validFood() {
        Food f = new Food();
        f.setId(1L);
        f.setFood_name("テスト食材");
        f.setCalorie(BigDecimal.valueOf(100));
        f.setProtein(BigDecimal.valueOf(10));
        f.setFat(BigDecimal.valueOf(5));
        f.setCarbo(BigDecimal.valueOf(20));
        f.setQuantity(BigDecimal.valueOf(100));
        return f;
    }
}
