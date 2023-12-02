package com.dogstore.dogstore.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogstore.dogstore.models.Customer;
import com.dogstore.dogstore.repository.CustomerRepository;

@CrossOrigin
@RestController
public class CustomerRestController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    // These PreAuthorizes don't seem to have any effect at the moment, possibly due to disabled
    // .csrf() in the WebSecurityConfig.java file
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Customer> getCustomersRest() {
        return (List<Customer>) customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<Customer> findById(@PathVariable("id") Long id) {
        return (Optional<Customer>) customerRepository.findById(id);
    }

    @CrossOrigin
    @PostMapping("/customers/{id}")
    public Customer addCustomerRest(Customer customer) {
        return customerRepository.save(customer);
    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
    }

}
