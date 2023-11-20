package com.dogstore.dogstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dogstore.dogstore.models.Customer;
import com.dogstore.dogstore.repository.CustomerRepository;

import jakarta.validation.Valid;

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	// Redirect to list of registered customer page.
	@GetMapping("/listcustomers")
	public String listOfCustomers(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "listcustomers";
	}

	// A add function for customers.

	// Move to /addcustomer -enpoint,
	// which has a form for the new customers to register.
	@GetMapping("/addcustomer")
	public String addCustomerForm(Model model) {
		model.addAttribute("Customer", new Customer());
		return "addcustomer";
	}

	// Add and save newly registered customer's info.

	// If there exist error result, it will return /addcustomer -endpoint.

	// Otherwise (ie. no errors) it will moves to /index -endpoint,
	// not /listcustomer -endpoint, since we don't want
	// newly registered customers see other customers' personal information
	// (a cyber security flaw).

	// Only admins (Omppu & Rane) have the acces rights for /listcustomer -enpoint.

	@PostMapping("/addcustomer")
	public String addProduct(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addcustomer";
		}
		customerRepository.save(customer);
		return "redirect:/index"; // Redirects to endpoint /index.html
	}

	// A delete function for registered customers.
	// It will erase all data and information
	// related to the given registered customer.

	// The code will first retrieve the given registered customer by their ID,
	// so it can also delete them from list of other registered customers.

	// When deleteById -method is executed, it will move former customers
	// back to /index -endpoint. Once again, the reason is that
	// only admins have access to /listcustomer -endpoint.

	@GetMapping("/deletemanufacturer/{id}")
	public String deleteManufacturer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("customer", customerRepository.findById(id));
		customerRepository.deleteById(id);

		return "redirect:/index"; // Redirects to endpoint /index.html

	}

}