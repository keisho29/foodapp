package com.example.foodapp.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class FoodCalcRequestDto {

	private Long id;

	@NotNull(message = "摂取量が未入力です")
	@PositiveOrZero(message = "摂取量が不正値です(0以上)")
	private BigDecimal intake;

	public FoodCalcRequestDto() {
	};

	public FoodCalcRequestDto(Long id, BigDecimal intake) {
		this.id = id;
		this.intake = intake;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getIntake() {
		return intake;
	}

	public void setIntake(BigDecimal intake) {
		this.intake = intake;
	}

}
