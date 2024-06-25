package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Order;

import jakarta.validation.Valid;

public interface IOrderService {
	
	List<Order> getOrdersFromDatabase();
	public Optional<Order> getOrdertById(int id);
	void deleteOrderById(int id);
	Order createOrder(@Valid Order newProduct);
	//ResponseEntity<Product> updateProduct(Integer productId);
	ResponseEntity<Order> updateOrder(Integer productId, @Valid Order newProduct);
	
}
