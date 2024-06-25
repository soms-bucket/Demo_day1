package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.IOrderService;

import jakarta.validation.Valid;

@RestController // http://localhost:9193/api/hello
@RequestMapping("/api")
public class OrderController {

	@Autowired
	IOrderService orderService;

	@GetMapping("/hello") // http://localhost:9193/api/hello
	String hello() {
		return "Hello World, Spring Boot.... Welcome to You!";
	}
	
	// http://localhost:9193/api/orders
	@GetMapping("/orders") 
	List<Order> orders() {
		// System.out.println("Running");
		return orderService.getOrdersFromDatabase();

	}

	// http://localhost:9193/api/orders/1
	@GetMapping("/orders/{id}")
	Optional<Order> findByOrderId(@PathVariable int id) throws ResourceNotFoundException {
		Optional<Order> product = orderService.getOrdertById(id);
		product.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		return product;
	}
	
	// http://localhost:9193/api/orders/1
	@DeleteMapping("/orders/{id}")
	void deleteOrderById(@PathVariable int id) {
		orderService.deleteOrderById(id);
	}

	// http://localhost:9193/api/orders
	@PostMapping("/orders")
	public Order createOrder(@Valid @RequestBody Order newProduct) {
		return orderService.createOrder(newProduct);
	}

	// http://localhost:9193/api/orders/1
	@PutMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder
	(@PathVariable(value = "id") Integer orderId,@Valid @RequestBody Order newOrder) {
		return orderService.updateOrder(orderId,newOrder);
	}

	// http://localhost:9193/api/orders/req?id=1
	@GetMapping("/orders/req")
	Optional<Order> findByProductIdUsingRequest(@RequestParam int id) {
		
		return orderService.getOrdertById(id);
	}
	
	// http://localhost:9193/api/orders/xml
	@GetMapping(path = "/orders/xml", produces = {MediaType.APPLICATION_XML_VALUE})
	List<Order> productsInXML() {
		// System.out.println("Running");
		return orderService.getOrdersFromDatabase();

	}
	
	
}
