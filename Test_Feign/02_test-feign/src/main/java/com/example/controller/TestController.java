package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feign.Test_Feign_Client;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	Test_Feign_Client testFeign;
	
	@GetMapping("/hello")
	String hello() {
		return "Hello World, Spring Boot.... Welcome to You!";
	}
	
	
	@GetMapping("/allproducts")
	public  ResponseEntity<String> getEmployees() {
	 return ResponseEntity.ok().body(testFeign.productResponse());
	}
	

}
