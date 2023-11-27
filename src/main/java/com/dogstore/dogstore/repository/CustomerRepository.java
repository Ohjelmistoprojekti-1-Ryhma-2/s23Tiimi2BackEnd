package com.dogstore.dogstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dogstore.dogstore.models.Customer;

@RepositoryRestResource
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByName(@Param("name") String name);
}
