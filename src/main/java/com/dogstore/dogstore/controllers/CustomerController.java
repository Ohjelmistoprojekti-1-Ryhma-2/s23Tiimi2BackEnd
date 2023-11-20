package com.dogstore.dogstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dogstore.dogstore.models.Customer;
import com.dogstore.dogstore.repository.CustomerRepository;

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

	// Move to /addcustomer -enpoint,
	// which has a form for new customers to register.
	@GetMapping("/addcustomer")
	public String addCustomerForm(Model model) {
		model.addAttribute("Customer", new Customer());
		return "addcustomer";
	}
}
