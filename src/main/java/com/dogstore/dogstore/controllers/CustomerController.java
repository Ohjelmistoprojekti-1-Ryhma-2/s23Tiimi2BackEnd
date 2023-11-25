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
		return "redirect:/customers"; // Redirect to list of registered customer page, i.e. /listcustomer.html.
	}

	// A delete function of registered customers,
	// only for admins (Omppu & Rane).
	@GetMapping("/deletecustomer/{id}")
	public String deleteCustomerByAdmins(@PathVariable("id") Long id, Model model) {
		model.addAttribute("customer", customerRepository.findById(id));
		customerRepository.deleteById(id);
		return "redirect:/customers";// Redirect to list of registered customer page, i.e. /listcustomer.html.
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

	// Otherwise (ie. no errors) it will moves to /index -endpoint,
	// not /listcustomer -endpoint, since we don't want
	// newly registered customers see other customers' personal information
	// (a cyber security flaw).

	// Only admins (Omppu & Rane) have the acces rights for /listcustomer -enpoint.

	@PostMapping("/addcustomer")
	public String addCustomer(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
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
	/*
	 * // When deleteById -method is executed, it will move former customers
	 * // back to /index -endpoint. Once again, the reason is that
	 * // only admins have access to /listcustomer -endpoint.
	 * 
	 * @GetMapping("/deletecustomer/{id}")
	 * public String deleteCustomerByCustomers(@PathVariable("id") Long id, Model
	 * model) {
	 * model.addAttribute("customer", customerRepository.findById(id));
	 * customerRepository.deleteById(id);
	 * 
	 * return "redirect:/index"; // Redirects to endpoint /index.html
	 * 
	 * }
	 */

}
