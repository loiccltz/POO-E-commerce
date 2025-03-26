package com.backend.ecommerce_backend.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PAYPAL")
public class PayPal extends PaymentMethod {
    private String email;
    private boolean verified;

    @Override
    public boolean processPayment(double amount) {
        // Logique de traitement de paiement PayPal
        if (validatePayment()) {
            System.out.println("Traitement du paiement PayPal de " + amount + "€");
            return true;
        }
        return false;
    }

    @Override
    public boolean validatePayment() {
        // Validation supplémentaire spécifique à PayPal
        return email != null && 
               email.contains("@") && 
               verified;
    }

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}