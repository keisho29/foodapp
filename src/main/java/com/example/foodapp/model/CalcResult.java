package com.example.foodapp.model;

import java.math.BigDecimal;
import java.util.List;

public class CalcResult {

	//	ドメインサービス内の計算結果受け取りクラス
	//	各栄養素の合計値と１食材分の明細を返す
	private final BigDecimal total_calorie;
	private final BigDecimal total_protein;
	private final BigDecimal total_fat;
	private final BigDecimal total_carbo;
	private final List<PerItemResult> perItemResults;

	public CalcResult(BigDecimal total_calorie, BigDecimal total_protein, BigDecimal total_fat,
			BigDecimal total_carbo,List<PerItemResult> perItemResults) {
		this.total_calorie = total_calorie;
		this.total_protein = total_protein;
		this.total_fat = total_fat;
		this.total_carbo = total_carbo;
		this.perItemResults = perItemResults;
	}

	public BigDecimal getTotal_calorie() {
		return total_calorie;
	}

	public BigDecimal getTotal_protein() {
		return total_protein;
	}

	public BigDecimal getTotal_fat() {
		return total_fat;
	}

	public BigDecimal getTotal_carbo() {
		return total_carbo;
	}

	public List<PerItemResult> getPerItemResults() {
		return perItemResults;
	}
	
	

}
