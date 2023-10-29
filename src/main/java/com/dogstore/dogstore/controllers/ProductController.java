package com.dogstore.dogstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.repository.ManufacturerRepository;
import com.dogstore.dogstore.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ManufacturerRepository manufacturerRepository;

	// Main page and listing of all products
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "home"; // home.html
	}

	// Retrieving a product by its ID for editing in the editproduct.html endpoint
	@GetMapping("/editproduct/{id}")
	public String editProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productRepository.findById(id));
		return "editproduct"; // editproduct.html
	}

	// Saving the retrieved and edited product into the repository
	@PostMapping("/saveproduct")
	public String saveProduct(Product product) {
		productRepository.save(product);
		return "redirect:/home"; // Redirect to endpoint /home.html
	}

	// Retrieving a product by its ID for removing
	@GetMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productRepository.findById(id));
		productRepository.deleteById(id);
		return "redirect:/home"; // Redirect to endpoint /home.html
	}

	// Retrieving a product by its ID for removing
	@GetMapping("/addproduct")
	public String addProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "addproduct";
	}

	@PostMapping("/addproduct")
	public String addProduct(@ModelAttribute Product product) {
		productRepository.save(product);
		return "redirect:/home";
	}

	// Redirects to list of manufacturers page.
	@GetMapping("/listmanufacturer")
	public String listOfManufacturers(Model model) {
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		return "listmanufacturer"; // Moves to endpoint /listmanufacturer.html.
	}

}
