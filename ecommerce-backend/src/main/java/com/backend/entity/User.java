package com.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Order> orderHistory;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    // Méthode pour l'enregistrement d'un nouvel utilisateur
    public void register() {
        // Dans une implémentation réelle, cette logique serait dans un service
        System.out.println("Enregistrement de l'utilisateur: " + username);
    }
    
    // Méthode pour la connexion d'un utilisateur
    public boolean login(String inputPassword) {
        // Dans une implémentation réelle, cette logique serait dans un service avec encodage du mot de passe
        return this.password.equals(inputPassword);
    }
    
    // Méthode pour consulter l'historique des commandes
    public List<Order> viewOrderHistory() {
        return this.orderHistory;
    }
    
    // Méthode polymorphique pour appliquer une réduction
    public double applyDiscount(double amount) {
        // Par défaut, pas de réduction
        return amount;
    }

    // Getters et Setters
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

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Cart getCart() {
        return cart;
    }
    
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}