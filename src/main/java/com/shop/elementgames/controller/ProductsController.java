package com.shop.elementgames.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.elementgames.exceptions.ProductNotFoundException;
import com.shop.elementgames.models.Product;
import com.shop.elementgames.repository.ProductRepository;

@RestController
@RequestMapping(path = "/api/products")
public class ProductsController {
	
	@Autowired private ProductRepository productRepository;
	
	@GetMapping("")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") UUID productId) {
		var product =  productRepository.findById(productId)
				.orElseThrow(ProductNotFoundException::new);
		
		return ResponseEntity.ok(product);
	}
	
	

}
