package com.dogstore.dogstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.dogstore.dogstore.repository.CustomerRepository;

import org.springframework.ui.Model;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public String listOfCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "listcustomers";
    }
}
