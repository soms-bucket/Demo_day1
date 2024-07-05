package com.example.customermanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.customermanagement.entity.Customer;

import jakarta.validation.Valid;

public interface ICustomerService {
	
	List<Customer> getCustomersFromDatabase();
	public Optional<Customer> getCustomerById(int id);
	void deleteCustomerById(int id);
	Customer createCustomer(@Valid Customer newCustomer);
	ResponseEntity<Customer> updateCustomer(Integer productId, @Valid Customer newCustomer);
	

}
