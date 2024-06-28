package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feign.Test_Feign_Client;
import com.example.model.FeignProduct;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	Test_Feign_Client testFeign;
	
	public static final String USER_SERVICE="TestService";
	private int attempt=1;
    
	
	@GetMapping("/hello")
	String hello() {
		return "Hello World, Spring Boot.... Welcome to You!";
	}
	
	// Applying Circuit_Breaker
	@GetMapping("/allproducts")
	@CircuitBreaker(name =USER_SERVICE,fallbackMethod = "getAllProducts")
    //@Retry(name = USER_SERVICE,fallbackMethod = "getAllProducts")	
	public  ResponseEntity<List<FeignProduct>> getProducts() {
        System.out.println("retry method called "+attempt++ +" times "+" at "+new Date());
        List<FeignProduct> lst=null;
        lst = testFeign.productResponse();
 
	 return ResponseEntity.ok().body(lst);
	}
	
	
	public List<FeignProduct> getAllProducts(){
        return Stream.of(
                new FeignProduct(119, "LED TV", 45000),
                new FeignProduct(345, "Headset",7000),
                new FeignProduct(475, "Sound bar", 13000),
                new FeignProduct(574, "Puma Shoes", 4600),
                new FeignProduct(678, "Vegetable chopper",999),
                new FeignProduct(532, "Oven Gloves", 745)
        ).collect(Collectors.toList());
    }
	

}

//http://localhost:9198/api/products
//http://localhost:9200/api/allproducts
//http://localhost:9200/actuator/health
