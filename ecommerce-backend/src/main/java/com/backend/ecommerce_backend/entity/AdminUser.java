package com.backend.ecommerce_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {

    public AdminUser() {
        
    }

    public AdminUser(String username, String email, String password) {
        super.setUsername(username);
        super.setEmail(email);
        super.setPassword(password);
    }
 
    @Override
    public BigDecimal applyDiscount(BigDecimal originalPrice) {
        // Remise de 15% pour les admin
        BigDecimal discountRate = new BigDecimal("0.15");
        return originalPrice.subtract(
            originalPrice.multiply(discountRate)
        ).setScale(2, RoundingMode.HALF_UP);
    }
}