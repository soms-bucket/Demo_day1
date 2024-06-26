package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient (name = "test-productservice", url = "${PRODUCT_SERVICE:http://localhost:9198}")

public interface Test_Feign_Client {
	
	@GetMapping("/api/products") 
	String productResponse();

}

// http://localhost:9198/api/products
// http://localhost:9200/api/allproducts
