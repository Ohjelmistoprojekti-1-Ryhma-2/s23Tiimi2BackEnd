package com.dogstore.dogstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.dogstore.dogstore.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
