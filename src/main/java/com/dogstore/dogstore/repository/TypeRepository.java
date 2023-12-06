package com.dogstore.dogstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.dogstore.dogstore.models.Type;

public interface TypeRepository extends CrudRepository<Type, Long> {

}
