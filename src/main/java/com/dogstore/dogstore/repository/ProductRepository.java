package com.dogstore.dogstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dogstore.dogstore.models.Product;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findByType(@Param("type") String type);
}
