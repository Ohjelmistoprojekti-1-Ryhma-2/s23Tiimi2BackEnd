package com.dogstore.dogstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.dogstore.dogstore.models.Product;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
