package com.dogstore.dogstore.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dogstore.dogstore.models.Customer;
import com.dogstore.dogstore.repository.CustomerRepository;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getCustomersRest() {
        return (List<Customer>) customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Optional<Customer> findById(@PathVariable("id") Long id) {
        return (Optional<Customer>) customerRepository.findById(id);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
    }

}
