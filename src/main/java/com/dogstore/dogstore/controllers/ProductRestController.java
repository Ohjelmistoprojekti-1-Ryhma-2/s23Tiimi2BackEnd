package com.dogstore.dogstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.repository.ProductRepository;

// @CrossOrigin
@RestController
public class ProductRestController {

	@Autowired
	private ProductRepository productRepository;

	// REST Products interface
	@GetMapping("/products")
	public List<Product> productsListRest() {
		return (List<Product>) productRepository.findAll();
	}

	// REST type search example:
	// http://localhost:8080/api/products/search/findByType?type=Sadetakki

	/*
	 * @GetMapping("/product/{id}")
	 * public Optional<Product> findProductRest(@PathVariable("id")
	 * Long id) { return productRepository.findById(id); }
	 * 
	 * // REST PUT
	 * @PutMapping("/products/{id}")
	 * public Product saveProductRest(Product product) { return
	 * productRepository.save(product); }
	 * 
	 * // REST DELETE
	 * 
	 * @DeleteMapping("/products/{id}")
	 * public String deleteProductRest(@PathVariable("id") Long id) {
	 * productRepository.deleteById(id); return "Deleted"; }
	 * 
	 * // REST POST
	 * 
	 * @PostMapping("/products")
	 * public Product addProductRest(Product product) { return
	 * productRepository.save(product); }
	 */

}
