package com.backend.ecommerce_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("REGULAR")
public class RegularUser extends User {

    public RegularUser() {
        
    }

    public RegularUser(String username, String email, String password) {
        super.setUsername(username);
        super.setEmail(email);
        super.setPassword(password);
    }
    
  
    @Override
    public BigDecimal applyDiscount(BigDecimal originalPrice) {
        // Aucune remise, retourne le prix original
        return originalPrice;
    }
}