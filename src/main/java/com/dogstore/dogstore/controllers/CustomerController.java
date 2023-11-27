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

	@GetMapping("/listcustomers")
	public String listOfCustomers(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "listcustomers";
	}

	// Edit function for registered customers.

	// Should this meant only for admins or customers?
	// (If customers, then they should not have access
	// to visit /listcustomer.html. Its for only admins). -Kaj

	// AFAIK the back end system is only accessible by the product owners (or
	// admins, Omppu & Rane).
	// A link to the shop itself is to be provided for any customers who
	// accidentally navigate to
	// the back end login page (to be implemented). Made some changes accordingly -
	// SJ

	@GetMapping("/editcustomer/{id}")
	public String editCustomer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("customers", customerRepository.findById(id));
		return "editcustomer";
	}

	// Saving the edited info of registered customers
	@PostMapping("/savecustomer")
	public String saveCustomer(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "editcustomer";
		}
		customerRepository.save(customer);
		return "redirect:/listcustomers"; // Redirect to list of registered customer page, i.e. /listcustomer.html.
	}

	// A delete function of registered customers,
	// only for admins (Omppu & Rane).
	@GetMapping("/deletecustomer/{id}")
	public String deleteCustomerByAdmins(@PathVariable("id") Long id, Model model) {
		model.addAttribute("customer", customerRepository.findById(id));
		customerRepository.deleteById(id);
		return "redirect:/listcustomers";// Redirect to list of registered customer page, i.e. /listcustomer.html.
	}

	// Move to /addcustomer -enpoint,
	// which has a form for new customers to register.
	@GetMapping("/addcustomer")
	public String addCustomerForm(Model model) {
		model.addAttribute("Customer", new Customer());
		return "addcustomer";
	}

	// Add and save newly registered customer's info.

	// If there exist error result, it will return /addcustomer -endpoint.

	@PostMapping("/addcustomer")
	public String addCustomer(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addcustomer";
		}
		customerRepository.save(customer);
		return "redirect:/listcustomers"; // Redirects to endpoint /listcustomers.html
	}

}
