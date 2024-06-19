package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
@Service
public class ProductService implements IProductService{
	
	@Autowired
	ProductRepository productRepo;
	
	@Override
	public List<Product> getProductsFromDatabase() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}
	
	@Override
	public Optional<Product> getProductById(int id) {
		
		return productRepo.findById(id);
	}

	@Override
	public void deleteProductById(int id) {
		// TODO Auto-generated method stub
		productRepo.deleteById(id);
		
	}
	
	
	
	

}
