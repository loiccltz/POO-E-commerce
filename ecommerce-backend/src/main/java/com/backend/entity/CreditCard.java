package com.backend.entity;

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
        System.out.println("Traitement du paiement par carte de crédit de " + amount + "€");
        return true;
    }
    
    // Getters et setters
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