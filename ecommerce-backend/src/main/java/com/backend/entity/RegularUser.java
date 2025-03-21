package com.backend.entity;

import jakarta.persistence.Entity;

@Entity
public class RegularUser extends User {
    private boolean premiumMember;
    
    public RegularUser() {
        this.setRole("USER");
    }
    
    // Polymorphisme: surcharge de applyDiscount
    public double applyDiscount(double amount) {
        // Les utilisateurs premium ont une réduction de 10%, les autres de 0%
        return premiumMember ? amount * 0.9 : amount;
    }
    
    // Getters et setters
    public boolean isPremiumMember() {
        return premiumMember;
    }
    
    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }
}