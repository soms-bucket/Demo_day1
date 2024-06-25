package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

import jakarta.validation.Valid;
@Service
public class OrderService implements IOrderService{
	//for getting log in console
	private final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	OrderRepository orderRepo;
	
	@Override
	public List<Order> getOrdersFromDatabase() {
		logger.info("Fetching all records from Database...");
		return orderRepo.findAll();
	}
	
	@Override
	public Optional<Order> getOrdertById(int id) {
		return orderRepo.findById(id);
	}

	@Override
	public void deleteOrderById(int id) {
		// TODO Auto-generated method stub
		orderRepo.deleteById(id);
		
	}

	@Override
	public Order createOrder(@Valid Order newOrder) {
		// TODO Auto-generated method stub
		return orderRepo.save(newOrder);
	}

	@Override
	public ResponseEntity<Order> updateOrder(Integer orderId, @Valid Order newOrder) {
		Optional<Order> existingOrder = orderRepo.findById(orderId);
		existingOrder.get().setFoodName(newOrder.getFoodName());
		existingOrder.get().setQnantity(newOrder.getQnantity());
		orderRepo.save(existingOrder.get());
		return ResponseEntity.ok(existingOrder.get());
	}
	
	
	
	
	

}
