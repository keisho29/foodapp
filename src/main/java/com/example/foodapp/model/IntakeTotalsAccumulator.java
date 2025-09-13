package com.example.foodapp.model;

import java.math.BigDecimal;

//ドメインサービス内のメインメソッドで算出した合計計算結果を保持しておくVO
//このクラスに保持される計算結果は桁数調整をしておらず、このクラスは保管目的のVO
public class IntakeTotalsAccumulator {
	private BigDecimal total_calorie=BigDecimal.ZERO;
	private BigDecimal total_protein=BigDecimal.ZERO;
	private BigDecimal total_fat=BigDecimal.ZERO;
	private BigDecimal total_carbo=BigDecimal.ZERO;
	
//	合計値が足されていくため、可変インスタンス
	public IntakeTotalsAccumulator() {}
	
//	加算メソッド
	
	public void addCalorie(BigDecimal calorie) {
		total_calorie=total_calorie.add(calorie);
	}

	public void addProtein(BigDecimal protein) {
		total_protein=total_protein.add(protein);
	}

	public void addFat(BigDecimal fat) {
		total_fat=total_fat.add(fat);
	}

	public void addCarbo(BigDecimal carbo) {
		total_carbo=total_carbo.add(carbo);
	}

//	ゲッター
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

	
	
	
}
