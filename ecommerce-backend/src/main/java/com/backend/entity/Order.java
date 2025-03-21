package com.backend.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;
    
    @ManyToOne
    private User user;
    
    // Utiliser ElementCollection au lieu de OneToMany pour CartItem
    @ElementCollection
    private List<CartItem> items = new ArrayList<>();
    
    private String status;
    
    // Ajouter getters et setters manquants
    public Long getOrderID() {
        return orderID;
    }
    
    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<CartItem> getItems() {
        return items;
    }
    
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public void placeOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeOrder'");
    }
    
    // Méthodes existantes...
}
