package com.backend.entity;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    private String productName;
    private double price;
    private int stockQuantity;

    // Nouvelle méthode getProductDetails()
    public Map<String, Object> getProductDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("id", productID);
        details.put("name", productName);
        details.put("price", price);
        details.put("stockQuantity", stockQuantity);
        return details;
    }

    // Getters et Setters existants
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void updateStock(int quantity) {
        this.stockQuantity -= quantity;
    }
    
    public Long getProductID() {
        return productID;
    }
    
    public void setProductID(Long productID) {
        this.productID = productID;
    }
}