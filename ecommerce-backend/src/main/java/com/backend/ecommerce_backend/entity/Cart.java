package com.backend.ecommerce_backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    private List<CartItem> items = new ArrayList<>();

   
    public Cart() {
        
    }

    public Cart(User user) {
        this.user = user;
    }

    // Getters and Setters
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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

    // Method to add a product to the cart
    public void addProduct(Product product, int quantity) {
        // Check if product already exists in cart
        for (CartItem item : items) {
            if (item.getProduct().getProductID().equals(product.getProductID())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        
        // If product not in cart, add new cart item
        CartItem newItem = new CartItem(product, quantity);
        items.add(newItem);
    }

    // Method to remove a product from the cart
    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().getProductID().equals(product.getProductID()));
    }

    // Method to calculate total price of items in cart
    public double calculateTotal() {
        return items.stream()
            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
            .sum();
    }

    // Nested class to represent cart items
    @Embeddable
    public static class CartItem {
        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;
        
        private int quantity;

        // Default constructor
        public CartItem() {}

        // Parameterized constructor
        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        // Getters and Setters
        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}