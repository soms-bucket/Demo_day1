package com.example.customermanagement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.exception.ResourceNotFoundException;
import com.example.customermanagement.repository.CustomerRepository;

public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private CustomerService customerService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSaveCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john.doe@example.com");
		customer.setPhone("1234567890");

		when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		Customer savedCustomer = customerService.createCustomer(customer);
		assertNotNull(savedCustomer);
		assertEquals("John", savedCustomer.getFirstName());
	}

	@Test
	public void testGetAllCustomers() {
		Customer customer1 = new Customer();
		customer1.setFirstName("John");
		Customer customer2 = new Customer();
		customer2.setFirstName("Jane");
		List<Customer> customerList = Arrays.asList(customer1, customer2);
		when(customerRepository.findAll()).thenReturn(customerList);
		List<Customer> result = customerService.getCustomersFromDatabase();
		assertEquals(2, result.size());
	}

	@Test
	public void testGetCustomerById() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setFirstName("John");

		when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		Optional<Customer> result = customerService.getCustomerById(1);
		assertTrue(result.isPresent());
		assertEquals("John", result.get().getFirstName());
	}

	@Test
	public void testUpdateCustomer() {
		Customer existingCustomer = new Customer();
		existingCustomer.setId(1);
		existingCustomer.setFirstName("John");
		when(customerRepository.existsById(1)).thenReturn(true);

		when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);
		when(customerRepository.findById(1)).thenReturn(Optional.of(existingCustomer));

		//existingCustomer.setFirstName("Bob");
		ResponseEntity<Customer> updatedCustomer = customerService.updateCustomer(1, existingCustomer);
		System.out.println("srvTest "+updatedCustomer.getBody());
		assertNotNull(updatedCustomer.getBody());
		assertEquals("John", updatedCustomer.getBody().getFirstName());

	}

	@Test
	public void testDeleteCustomer() {
		int customerId = 1;
		when(customerRepository.existsById(customerId)).thenReturn(true);
		doNothing().when(customerRepository).deleteById(customerId);
		assertDoesNotThrow(() -> customerService.deleteCustomerById(customerId));
	}

	@Test
	public void testDeleteCustomerThrowsException() {
		int customerId = 1;
		// System.out.println(customerService.deleteCustomerById(customerId));
		when(customerRepository.existsById(customerId)).thenReturn(false);
		assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomerById1(customerId));
	}

}
