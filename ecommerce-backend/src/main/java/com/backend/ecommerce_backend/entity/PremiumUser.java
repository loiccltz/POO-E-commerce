package com.backend.ecommerce_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@DiscriminatorValue("PREMIUM")
public class PremiumUser extends User {
    // Constructeurs
    public PremiumUser() {}

    public PremiumUser(String username, String email, String password) {
        super.setUsername(username);
        super.setEmail(email);
        super.setPassword(password);
    }
    
    // Implémentation de la méthode de remise pour un utilisateur premium
    @Override
    public BigDecimal applyDiscount(BigDecimal originalPrice) {
        // Remise de 10%
        BigDecimal discountRate = new BigDecimal("0.10");
        return originalPrice.subtract(
            originalPrice.multiply(discountRate)
        ).setScale(2, RoundingMode.HALF_UP);
    }
}