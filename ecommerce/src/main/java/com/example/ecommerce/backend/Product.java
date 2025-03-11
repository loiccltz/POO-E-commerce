package com.example.ecommerce.backend;

public class Product {
    private String productName;
    private  float productPrice;
    private  int productID;
    private  int stockQuantity;

    public Product(String productName, float productPrice, int productID, int stockQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productID = productID;
        this.stockQuantity = stockQuantity;
    }

    public void updateStock(int quantity) {
        this.stockQuantity -= quantity;
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public String getProductdetails() {
        return "Product Name: " + productName + " Product Price: " + productPrice + " Product ID: " + productID + " Stock Quantity: " + stockQuantity;
    }
    public String getProductName() {
        return productName;
    }
    public float getProductPrice() {
        return productPrice;
    }
    public int getProductID() {
        return productID;
    }
    
}
