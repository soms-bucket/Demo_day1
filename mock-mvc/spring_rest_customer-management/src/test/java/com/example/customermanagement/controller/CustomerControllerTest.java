package com.example.customermanagement.controller;

import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;
	@InjectMocks
	private CustomerController customerController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testCreateCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john.doe@example.com");
		customer.setPhone("1234567890");

		when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);
		mockMvc.perform(post("/api/customers").contentType(MediaType.APPLICATION_JSON).content(
				"{ \"firstName\": \"John\", \"lastName\": \"Doe\",\"email\": \"john.doe@example.com\", \"phone\": \"1234567890\" }"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("John"));
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		Customer customer1 = new Customer();
		customer1.setFirstName("John");
		Customer customer2 = new Customer();
		customer2.setFirstName("Jane");
		when(customerService.getCustomersFromDatabase()).thenReturn(Arrays.asList(customer1, customer2));
		mockMvc.perform(get("/api/customers")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[1].firstName").value("Jane"));
	}

	@Test
	public void testGetCustomerById() throws Exception {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setFirstName("John");

		when(customerService.getCustomerById(1)).thenReturn(Optional.of(customer));
		mockMvc.perform(get("/api/customers/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("John"));
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john.doe@example.com");
		customer.setPhone("1234567890");

		when(customerService.getCustomerById(1)).thenReturn(Optional.of(customer));
		when(customerService.updateCustomer(eq(1),any(Customer.class))).thenReturn(ResponseEntity.ok(customer));
		mockMvc.perform(put("/api/customers/1").contentType(MediaType.APPLICATION_JSON).content(
				"{ \"firstName\": \"John\", \"lastName\": \"Doe\","
				+ "\"email\": \"john.doe@example.com\", \"phone\": \"1234567890\" }"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("John"))
				.andExpect(jsonPath("$.lastName").value("Doe"));
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		mockMvc.perform(delete("/api/customers/1")).andExpect(status().isNoContent());
	}

}
