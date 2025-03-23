package com.backend.ecommerce_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.ecommerce_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
