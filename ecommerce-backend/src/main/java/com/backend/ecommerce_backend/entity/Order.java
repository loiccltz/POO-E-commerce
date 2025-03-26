package com.backend.ecommerce_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    private double totalAmount;

    // Enum for order status
    public enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    // Default constructor
    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    // Getters and Setters
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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Method to place an order
    public void placeOrder(List<Cart.CartItem> cartItems) {
        // Convert cart items to order items
        for (Cart.CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity());
            items.add(orderItem);

            // Update product stock
            cartItem.getProduct().updateStock(cartItem.getQuantity());
        }

        // Calculate total amount
        this.totalAmount = cartItems.stream()
            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
            .sum();

        // Set order status to processing
        this.status = OrderStatus.PROCESSING;
    }

    // Method to update order status
    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    // Nested class to represent order items
    @Embeddable
    public static class OrderItem {
        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;
        
        private int quantity;
        private double price;

        // Default constructor
        public OrderItem() {}

        // Parameterized constructor
        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
            this.price = product.getPrice();
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}