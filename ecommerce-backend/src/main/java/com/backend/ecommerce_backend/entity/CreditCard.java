package com.backend.ecommerce_backend.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CREDIT_CARD")
public class CreditCard extends PaymentMethod {
    private String cardNumber;
    private String cardholderName;
    private String expiryDate;
    private String cvv;

    @Override
    public boolean processPayment(double amount) {
        // Logique de traitement de paiement par carte de crédit
        if (validatePayment()) {
            System.out.println("Traitement du paiement par carte de crédit de " + amount + "€");
            return true;
        }
        return false;
    }

    @Override
    public boolean validatePayment() {
        // Validation supplémentaire spécifique à la carte de crédit
        return cardNumber != null && 
               cardNumber.length() >= 13 && 
               cardNumber.length() <= 19 &&
               cvv != null && 
               cvv.length() == 3;
    }

    // Getters et Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}