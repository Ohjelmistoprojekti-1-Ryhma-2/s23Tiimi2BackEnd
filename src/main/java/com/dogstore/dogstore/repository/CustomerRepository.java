package com.dogstore.dogstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.dogstore.dogstore.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
