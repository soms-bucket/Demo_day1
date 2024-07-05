package com.example.customermanagement.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.exception.ResourceNotFoundException;
import com.example.customermanagement.repository.CustomerRepository;

import jakarta.validation.Valid;
@Service
public class CustomerService implements ICustomerService{
	//for getting log in console
	private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Override
	public List<Customer> getCustomersFromDatabase() {
		logger.info("Fetching all records from Database...");
		return customerRepo.findAll();
	}
	
	@Override
	public Optional<Customer> getCustomerById(int id) {
		
		return customerRepo.findById(id);
	}

	@Override
	public void deleteCustomerById(int id) {
		// TODO Auto-generated method stub
		customerRepo.deleteById(id);
		
	}
	public void deleteCustomerById1(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Customer> cs = customerRepo.findById(id);
		cs.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		customerRepo.deleteById(id);
		
	}

	@Override
	public Customer createCustomer(@Valid Customer newCustomer) {
		// TODO Auto-generated method stub
		return customerRepo.save(newCustomer);
	}

	@Override
	public ResponseEntity<Customer> updateCustomer(Integer customerId, @Valid Customer newCustomer) {
		
		System.out.println(" Service on mood: "+newCustomer);
		Optional<Customer> existingCustomer = customerRepo.findById(customerId);
		
		existingCustomer.get().setFirstName(newCustomer.getFirstName());
		existingCustomer.get().setLastName(newCustomer.getLastName());
		existingCustomer.get().setEmail(newCustomer.getEmail());
		customerRepo.save(existingCustomer.get());
		return ResponseEntity.ok(existingCustomer.get());
	}
	
	

}
