package com.dogstore.dogstore.repository;

import com.dogstore.dogstore.models.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
