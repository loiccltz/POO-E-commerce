package com.example.ecommerce.backend;
import java.util.List;
import java.util.ArrayList;

public class User {
    private String username;
    private  String email; 
    private String password;
    private List<Order> orderHistory = new ArrayList<Order>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
    
    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }
    
}
