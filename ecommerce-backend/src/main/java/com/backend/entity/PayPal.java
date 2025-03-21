package com.backend.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PAYPAL")
public class PayPal extends PaymentMethod {
    private String email;
    private String password;
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Traitement du paiement PayPal de " + amount + "€");
        return true;
    }
    
    // Getters et setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}