package com.backend.entity;

import jakarta.persistence.Entity;

@Entity
public class AdminUser extends User {
    private String adminLevel;
    
    public AdminUser() {
        this.setRole("ADMIN");
    }
    
    // Polymorphisme: surcharge de applyDiscount
    public double applyDiscount(double amount) {
        // Les admins ont une réduction spéciale de 15%
        return amount * 0.85;
    }
    
    // Méthodes spécifiques aux admins
    public boolean canManageProducts() {
        return true;
    }
    
    public boolean canManageUsers() {
        return "HIGH".equals(adminLevel);
    }
    
    // Getters et setters
    public String getAdminLevel() {
        return adminLevel;
    }
    
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
}