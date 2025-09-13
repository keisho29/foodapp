package com.example.foodapp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class CalcFoodTest {

    @Test
    void 正しい引数を渡したときにコンストラクタが生成できるか() {
    	
        // 第一引数foodを定義
        Food food = new Food();
        
//        第二引数intakeを定義
        BigDecimal intake = BigDecimal.valueOf(150);

//        コンストラクタ呼び出し、生成
        CalcFood calcFood = new CalcFood(food, intake);

     // 定義したfoodとcalcFoodのfoodフィールドが同じインスタンスを参照しているか確認
        assertSame(food, calcFood.getFood());  
        
     // 値が一致するか確認
        assertEquals(intake, calcFood.getIntake()); 
    }

    @Test
    void intakeが0許容されているか() {
        Food food = new Food();
        BigDecimal intake = BigDecimal.ZERO;

        CalcFood calcFood = new CalcFood(food, intake);

        assertEquals(BigDecimal.ZERO, calcFood.getIntake());
    }
}
