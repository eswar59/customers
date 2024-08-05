package com.piggy.customers.repository;

import com.piggy.customers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
