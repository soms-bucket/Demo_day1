package com.example.customermanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.exception.ResourceNotFoundException;
import com.example.customermanagement.service.ICustomerService;

import jakarta.validation.Valid;

@RestController // http://localhost:9192/api
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	
	@GetMapping("/hello")
	String hello() {
		return "Hello World, Spring Boot.... Welcome to You!";
	}
	
	// http://localhost:9192/api/customers
	@GetMapping("/customers")
	List<Customer> customers() {
		// System.out.println("Running");
		return customerService.getCustomersFromDatabase();

	}

	// http://localhost:9192/api/customers/1
	@GetMapping("/customers/{id}")
	Optional<Customer> findCustomerById(@PathVariable int id) throws ResourceNotFoundException {
		Optional<Customer> product = customerService.getCustomerById(id);
		product.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		return product;
	}

	// http://localhost:9192/api/customers/1
	@DeleteMapping("/customers/{id}")
	Object deleteCustomerById(@PathVariable int id) {
		customerService.deleteCustomerById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// http://localhost:9192/api/customers
	@PostMapping("/customers")
	public Customer createCustomer(@Valid @RequestBody Customer newCustomer) {
		return customerService.createCustomer(newCustomer);
	}

	// http://localhost:9192/api/customers/1
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer
	(@PathVariable(value = "id") Integer customerId,@Valid @RequestBody Customer newCustomer) {
		System.out.println(" con: "+newCustomer);
		return customerService.updateCustomer(customerId,newCustomer);
	}
	
}
