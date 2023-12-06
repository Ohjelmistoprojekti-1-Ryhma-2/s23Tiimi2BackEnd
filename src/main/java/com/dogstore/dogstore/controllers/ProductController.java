package com.dogstore.dogstore.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.models.Type;
import com.dogstore.dogstore.repository.ManufacturerRepository;
import com.dogstore.dogstore.repository.ProductRepository;

import jakarta.validation.Valid;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ManufacturerRepository manufacturerRepository;

	// Listing of all products
	@GetMapping("/listproducts")
	public String home(Model model) {
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		return "listproducts"; // listproducts.html
	}

	// Test REST api for products, including manufacturer in non-HAL format
	@GetMapping("/test")
	public @ResponseBody List<Product> test() {
		return (List<Product>) productRepository.findAll();
	}

	// Retrieving a product by its ID for editing in the editproduct.html endpoint
	@GetMapping("/editproduct/{id}")
	public String editProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productRepository.findById(id));
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		return "editproduct"; // editproduct.html
	}

	// Saving the retrieved and edited product into the repository.
	@PostMapping("/saveproduct")
	public String saveProduct(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("manufacturers", manufacturerRepository.findAll());
			return "editproduct"; // Stay on the form page and display errors
		}
		productRepository.save(product);
		return "redirect:/listproducts";
	}

	// Retrieving a product by its ID for removing
	@GetMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productRepository.findById(id));
		productRepository.deleteById(id);
		return "redirect:/listproducts"; // Redirect to endpoint /listproducts.html
	}

	// Move to /addproduct -endpoint,
	// which has a form for the new product.

	@GetMapping("/addproduct")
	public String addProductForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		return "addproduct";
	}

	// Add and save the new product.
	// Moves back to /listproducts -endpoint.

	/* TODO: Update setSize from Type entity. */

	@PostMapping("/addproduct")
	public String addProduct(@Valid @ModelAttribute Product product, Type type, BindingResult result, Model model) {
		if (!Arrays.asList("food", "clothing", "toy").contains(type.getCategory())) {
			result.rejectValue("type", "error.product", "Invalid product type");
		}

		// Set size as "-" if type is not "clothing"
		if (!"clothing".equals(type.getCategory())) {
			type.setSize("-");
		} else {
			if (!Arrays.asList("S", "M", "L").contains(type.getSize())) {
				result.rejectValue("size", "error.product", "Invalid size for clothing");
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("manufacturers", manufacturerRepository.findAll());
			return "addproduct";
		}
		// save product
		productRepository.save(product);
		return "redirect:/listproducts";
	}

	@GetMapping("/productsbymanufacturer")
	public String getProductsByManufacturer(@RequestParam("manufacturerId") Long manufacturerId, Model model) {
		List<Product> products = productRepository.findByManufacturerId(manufacturerId);
		model.addAttribute("products", products);

		model.addAttribute("manufacturers", manufacturerRepository.findAll());

		return "listproducts";
	}

}
