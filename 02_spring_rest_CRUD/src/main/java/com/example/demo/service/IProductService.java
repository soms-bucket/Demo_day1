package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Product;

public interface IProductService {
	
	List<Product> getProductsFromDatabase();
	public Optional<Product> getProductById(int id);
	void deleteProductById(int id);

}
