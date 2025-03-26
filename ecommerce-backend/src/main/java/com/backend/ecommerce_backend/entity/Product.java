package com.backend.ecommerce_backend.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    
    private String productName;
    private double price;
    private int stockQuantity;


    public Product() {
    }

    public Product(String productName, double price, int stockQuantity) {
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

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

    @JsonIgnore
    public ProductDetails getProductDetails() {
        return new ProductDetails(this.productID, this.productName, this.price, this.stockQuantity);
    }


    public static class ProductDetails {
        private Long productID;
        private String productName;
        private double price;
        private int stockQuantity;

        public ProductDetails(Long productID, String productName, double price, int stockQuantity) {
            this.productID = productID;
            this.productName = productName;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }

        // Getters
        public Long getProductID() {
            return productID;
        }

        public String getProductName() {
            return productName;
        }

        public double getPrice() {
            return price;
        }

        public int getStockQuantity() {
            return stockQuantity;
        }
    }

    public void updateStock(int quantity) {
        if (quantity > this.stockQuantity) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        this.stockQuantity -= quantity;
    }
}