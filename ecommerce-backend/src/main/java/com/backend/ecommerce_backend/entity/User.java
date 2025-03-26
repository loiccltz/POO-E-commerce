package com.backend.ecommerce_backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
//import java.util.List;

@Entity
@Table(name = "users") // user est deja réservé dans SQL 
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    //private String role;

    // @OneToMany(mappedBy = "user")
    // private List<Order> orderHistory;

    // Getters and Setters

     public abstract BigDecimal applyDiscount(BigDecimal originalPrice);


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public List<Order> getOrderHistory() {
    //     return orderHistory;
    // }
}