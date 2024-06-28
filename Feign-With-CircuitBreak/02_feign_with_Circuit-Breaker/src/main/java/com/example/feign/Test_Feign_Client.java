package com.example.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.FeignProduct;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient (name = "test-productservice", url = "${PRODUCT_SERVICE:http://localhost:9198}")

public interface Test_Feign_Client {
	
	
	@GetMapping("/api/products")	
	List<FeignProduct> productResponse();
	
	

}

