package com.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;
    
    @ManyToOne
    private User user;
    
    @OneToMany
    private List<CartItem> items;
    private String status;

    public void placeOrder() {
        for (CartItem item : items) {
            item.getProduct().updateStock(item.getQuantity());
        }
        this.status = "Processing";
    }
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}
