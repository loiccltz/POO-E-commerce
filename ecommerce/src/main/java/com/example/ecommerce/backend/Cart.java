package com.example.ecommerce.backend;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items;

    public Cart() {
         this.items = new ArrayList<Product>();
    }


    public void addProduct(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.items.add(product);
        }
    }
    public void removeProduct(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.items.remove(product);
        }
    }
    public List<Product> getItems() {
        return items;
    }
    public void clearCart() {
        this.items.clear();
    }
    public float getTotal() {
        float total = 0;
        for (Product product : items) {
            total += product.getProductPrice();
        }
        return total;
    }
    
    
}
