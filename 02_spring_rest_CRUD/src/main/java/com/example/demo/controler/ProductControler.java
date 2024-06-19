package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.IProductService;

@RestController // http://localhost:9191/api/hello
@RequestMapping("/api")
public class ProductControler {
	
	@Autowired
	IProductService productService;
	
	@GetMapping("/hello")
    String hello() {
        return "Hello World, Spring Boot.... Welcome to You!";
    }
	@GetMapping("/products")
	List<Product> products() {
		//System.out.println("Running");
		return productService.getProductsFromDatabase();
		
	}
	// http://localhost:9191/api/products/1
	@GetMapping("/products/{id}")
	Optional<Product>findByProductId (@PathVariable int id){
		return productService.getProductById(id);
	}
	
	@DeleteMapping("/products/{id}")
	void deleteProductById(@PathVariable int id) {
		productService.deleteProductById(id);
	}
	
	
	
	
	
	
	
}
