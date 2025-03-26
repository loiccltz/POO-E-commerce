package com.backend.ecommerce_backend.repository;

import com.backend.ecommerce_backend.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
