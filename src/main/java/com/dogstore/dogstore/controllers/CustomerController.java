package com.dogstore.dogstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dogstore.dogstore.models.Customer;
import com.dogstore.dogstore.repository.CustomerRepository;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public String listOfCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "listcustomers";
    }

    @GetMapping("/editcustomer/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customers", customerRepository.findById(id));
        return "editcustomer";
    }

    @PostMapping("/savecustomer")
    public String saveCustomer(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editcustomer";
        }
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id));
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }
}
