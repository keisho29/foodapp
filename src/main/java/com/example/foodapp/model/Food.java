package com.example.foodapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="foods")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,unique=true)
	private String food_name;
	
	@Column(nullable = false)
	private BigDecimal calorie;
	
	@Column(nullable = false)
	private BigDecimal protein;
	
	@Column(nullable = false)
	private BigDecimal fat;
	
	@Column(nullable = false)
	private BigDecimal carbo;
	
	@Column(nullable = false)
	@CreatedDate
	private LocalDateTime create_date;
	
	@Column(nullable = false)
	private BigDecimal quantity;
	
//	デフォルトコンストラクタ
	public Food() {}
			
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}


	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public BigDecimal getCalorie() {
		return calorie;
	}

	public void setCalorie(BigDecimal calorie) {
		this.calorie = calorie;
	}

	public BigDecimal getProtein() {
		return protein;
	}

	public void setProtein(BigDecimal protein) {
		this.protein = protein;
	}

	public BigDecimal getFat() {
		return fat;
	}

	public void setFat(BigDecimal fat) {
		this.fat = fat;
	}

	public BigDecimal getCarbo() {
		return carbo;
	}

	public void setCarbo(BigDecimal carbo) {
		this.carbo = carbo;
	}

	public LocalDateTime getCreate_date() {
		return create_date;
	}


	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
