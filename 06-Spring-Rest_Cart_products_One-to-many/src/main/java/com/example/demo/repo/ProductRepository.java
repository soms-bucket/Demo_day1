package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.demo.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Double> {
		
	List<Products> findByPnameStartingWithIgnoreCase(String prefix);
	
	@Query(nativeQuery = true, value = "select * from cart_products where price>1000")
	List<Products> findByPrice();
}
