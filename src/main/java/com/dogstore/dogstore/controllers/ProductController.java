package com.dogstore.dogstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dogstore.dogstore.models.Product;
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
		return "listproducts"; // listproducts.html
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

	@PostMapping("/addproduct")
	public String addProduct(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
		 if (!"clothing".equals(product.getType())) {
        product.setSize("-"); // or "No size" as per your requirement
    }

		if (result.hasErrors()) {
			model.addAttribute("manufacturers", manufacturerRepository.findAll());
			return "addproduct";
		}
		productRepository.save(product);
		return "redirect:/listproducts";
	}

}
