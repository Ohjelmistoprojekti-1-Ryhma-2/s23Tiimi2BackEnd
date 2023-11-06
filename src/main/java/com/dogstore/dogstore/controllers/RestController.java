package com.dogstore.dogstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.repository.ProductRepository;

@Controller
public class RestController {

    @Autowired
    private ProductRepository productRepository;

    // REST interface
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public @ResponseBody List<Product> productsListRest() {
        return (List<Product>) productRepository.findAll();
    }

}
