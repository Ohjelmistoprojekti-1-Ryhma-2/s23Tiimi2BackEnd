package com.dogstore.dogstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dogstore.dogstore.models.Product;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {

}
