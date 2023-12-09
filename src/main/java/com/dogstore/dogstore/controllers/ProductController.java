package com.dogstore.dogstore.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
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
import com.dogstore.dogstore.repository.TypeRepository;

import jakarta.validation.Valid;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	TypeRepository typeRepository;

	@Autowired
	ManufacturerRepository manufacturerRepository;

	// Listing of all products
	@GetMapping("/listproducts")
	// All PreAuthorizations disabled at the moment to ease production
	// @PreAuthorize("hasAuthority('ADMIN')")
	public String listProducts(Model model) {
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("types", typeRepository.findAll());
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
	// @PreAuthorize("hasAuthority('ADMIN')")
public String editProduct(@PathVariable("id") Long id, Model model) {
    Optional<Product> productOpt = productRepository.findById(id);

    if (!productOpt.isPresent()) {
        // Handle the case where the product is not found
        return "redirect:/listproducts"; // or an error page
    }

    // Unwrap the Optional and add the Product object to the model
    model.addAttribute("product", productOpt.get());
    model.addAttribute("types", typeRepository.findAll());
    model.addAttribute("manufacturers", manufacturerRepository.findAll());
    return "editproduct"; // editproduct.html
}

	// Saving the retrieved and edited product into the repository.
	@PostMapping("/saveproduct")
	// @PreAuthorize("hasAuthority('ADMIN')")
	public String saveProduct(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
		Long typeId = product.getType().getId();
		Optional<Type> typeOpt = typeRepository.findById(typeId);

		if (typeOpt.isPresent()) {
			product.setType(typeOpt.get());
			// Set size logic
			if (!"clothing".equalsIgnoreCase(typeOpt.get().getCategory())) {
				product.setSize("-");
			} else if (!Arrays.asList("S", "M", "L").contains(product.getSize())) {
				result.rejectValue("size", "error.product", "Invalid size for clothing");
			}
		} else {
			result.rejectValue("type", "error.product", "Invalid product type");
		}
		if (result.hasErrors()) {
			model.addAttribute("types", typeRepository.findAll());
			model.addAttribute("manufacturers", manufacturerRepository.findAll());
			return "editproduct"; // Stay on the form page and display errors
		}
		productRepository.save(product);
		return "redirect:/listproducts";
	}

	// Retrieving a product by its ID for removing
	@GetMapping("/deleteproduct/{id}")
	// @PreAuthorize("hasAuthority('ADMIN')")
	public String deleteProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productRepository.findById(id));
		productRepository.deleteById(id);
		return "redirect:/listproducts"; // Redirect to endpoint /listproducts.html
	}

	// Move to /addproduct -endpoint,
	// which has a form for the new product.

	@GetMapping("/addproduct")
	// @PreAuthorize("hasAuthority('ADMIN')")
	public String addProductForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("types", typeRepository.findAll());
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		return "addproduct";
	}

	// Add and save the new product.
	// Moves back to /listproducts -endpoint.

	@PostMapping("/addproduct")
	public String addProduct(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
		Type type = product.getType();

		// Set size as "-" if type is not "clothing"
		if (!"clothing".equalsIgnoreCase(type.getCategory())) {
			product.setSize("-");
		} else {
			// For "clothing", ensure size is one of "S", "M", "L"
			if (!Arrays.asList("S", "M", "L").contains(product.getSize())) {
				result.rejectValue("size", "error.product", "Invalid size for clothing");
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("types", typeRepository.findAll());
			model.addAttribute("manufacturers", manufacturerRepository.findAll());
			return "addproduct";
		}

		productRepository.save(product);
		return "redirect:/listproducts";
	}
	@GetMapping("/productsbymanufacturer")
	// @PreAuthorize("hasAuthority('ADMIN')")
	public String getProductsByManufacturer(@RequestParam("manufacturerId") Long manufacturerId, Model model) {
		List<Product> products = productRepository.findByManufacturerId(manufacturerId);
		model.addAttribute("products", products);

		model.addAttribute("manufacturers", manufacturerRepository.findAll());

		return "listproducts";
	}

}
