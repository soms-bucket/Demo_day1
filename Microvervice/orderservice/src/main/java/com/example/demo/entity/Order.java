package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {
	
	private int orderId;  
	@NotNull
	private String foodName;
	private int qnantity ;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    public Order(int orderId, String foodName, int qnantity) {
		super();
		this.orderId = orderId;
		this.foodName = foodName;
		this.qnantity = qnantity;
	}
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return orderId;
	}
	public void setId(int orderId) {
		this.orderId = orderId;
	}
	
	
	@Column(name = "Food_name", nullable = false)
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	@Column(name = "Quantity", nullable = false)
	
	public int getQnantity() {
		return qnantity;
	}
	public void setQnantity(int qnantity) {
		this.qnantity = qnantity;
	}
	
	
}
