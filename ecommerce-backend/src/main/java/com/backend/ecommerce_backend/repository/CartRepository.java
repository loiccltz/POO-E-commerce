package com.backend.ecommerce_backend.repository;

import com.backend.ecommerce_backend.entity.Cart;
import com.backend.ecommerce_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}