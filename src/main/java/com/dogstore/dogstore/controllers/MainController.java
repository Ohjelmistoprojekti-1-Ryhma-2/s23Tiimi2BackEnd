package com.dogstore.dogstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dogstore.dogstore.repository.ProductRepository;

@Controller
public class MainController {

    @Autowired
    private ProductRepository pRepository;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("products", pRepository.findAll());
        return "home";
    }
}
