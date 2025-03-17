package com.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private User user;
    
    @ElementCollection
    private List<CartItem> items;

    public void addProduct(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }
    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }
    public double calculateTotal() {
        return items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }
}

@Embeddable
class CartItem {
    @ManyToOne
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}