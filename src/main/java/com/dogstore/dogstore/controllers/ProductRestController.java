package com.dogstore.dogstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.repository.ProductRepository;

@RestController
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    // REST Products interface
    @GetMapping(value = "/products")
    public List<Product> productsListRest() {
        return (List<Product>) productRepository.findAll();
    }

}
