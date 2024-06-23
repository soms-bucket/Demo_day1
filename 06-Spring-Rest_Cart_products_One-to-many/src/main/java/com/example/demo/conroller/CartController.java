package com.example.demo.conroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Products;
import com.example.demo.repo.CartRepository;
import com.example.demo.repo.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;

	// http://localhost:9196/api/carts
	@GetMapping("/carts")
	public List<Cart> getCarts() {
		return cartRepository.findAll();
	}

	@GetMapping("/carts/{id}")
	public ResponseEntity<Cart> getCartById(@PathVariable(value = "id") Long cartId) throws ResourceNotFoundException {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found :: " + cartId));

		return ResponseEntity.ok().body(cart);
	}

	// http://localhost:9196/api/carts
	@PostMapping("/carts")
	public Cart createCart(@Valid @RequestBody Cart cart) {
		return cartRepository.save(cart);
	}

	@PutMapping("/carts/{id}")
	public ResponseEntity<Cart> updateCart(@PathVariable(value = "id") Long cartId,
			@Valid @RequestBody Cart cartDetails) throws ResourceNotFoundException {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found :: " + cartId));

		cart.setName(cartDetails.getName());
		final Cart updatedCart = cartRepository.save(cart);
		return ResponseEntity.ok(updatedCart);
	}

	// http://localhost:9196/api/carts/
	@DeleteMapping("/carts/{id}")
	public Map<String, Boolean> deleteCart(@PathVariable(value = "id") Long cartId) throws ResourceNotFoundException {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found :: " + cartId));

		cartRepository.delete(cart);// (cart);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	// http://localhost:9196/api/carts/checkout/1
	@GetMapping("/carts/checkout/{id}")
	public Map<String, Long> getCartBillById(@PathVariable(value = "id") Long cartId) throws ResourceNotFoundException {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found :: " + cartId));

		List<Products> list = cart.getProducts();
		Map<String, Long> response = new HashMap<>();
		long price = 0;
		for (Products pr : list)
			price += pr.getPrice();
		response.put("Total Price: ", price);
		return response;
	}

	// Cart-Level
	// http://localhost:9196/api/carts/price/1
	@GetMapping("/carts/price/{id}")
	public Map<String, Double> getCartProductByPrice(@PathVariable(value = "id") Long cartId)
			throws ResourceNotFoundException {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found :: " + cartId));

		List<Products> list = cart.getProducts();
		Map<String, Double> response = new HashMap<>();
		for (Products pr : list) {
			if (pr.getPrice() > 1000) {
				response.put(pr.getPname(), pr.getPrice());
			}
		}
		return response;
	}

	// Cart-Level
	// http://localhost:9196/api/carts/pro/3
	@GetMapping("/carts/pro/{id}")
	public List<String> getProductCartById(@PathVariable(value = "id") Long cartId) throws ResourceNotFoundException {

		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found :: " + cartId));

		List<Products> list = cart.getProducts();
		List<String> name = new ArrayList<>();
		for (Products pr : list) {
			if (pr.getPname().charAt(0) == 'A')
				name.add(pr.getPname());
		}
		return name;
	}

	// Product-Level
	// http://localhost:9196/api/carts/pro?prefix=A
	@GetMapping("/carts/pro")
	// for String_Variable using @RequestParam
	public List<Products> getProducByPrefix(@RequestParam String  prefix) {
		List<Products> proList = productRepository.findByPnameStartingWithIgnoreCase(prefix);
		return proList;
	}

	// Product-Level
	// http://localhost:9196/api/carts/price
	@GetMapping("/carts/price")
	// for String_Variable using @RequestParam
	public List<Products> getProductByPrice() {
		List<Products> proList = productRepository.findByPrice();
		return proList;
	}
	
	
	
	// http://localhost:9196/api/pushdemo
	@GetMapping("/pushdemodata")
	public void pushDemoData() {

		Cart cart = new Cart();
        cart.setName("My Cart");

        Products product1 = new Products();
        product1.setPname("Bread");
        product1.setPrice(500.0);
       
        Products product2 = new Products();
        product2.setPname("Butter");
        product2.setPrice(1500.0);
        
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
		
        cartRepository.save(cart);
	}

}
