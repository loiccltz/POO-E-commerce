package com.backend.ecommerce_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type")
public abstract class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createdAt;

    // Constructeur par défaut
    public PaymentMethod() {
        this.createdAt = LocalDateTime.now();
    }

    // Méthode abstraite pour traiter le paiement
    public abstract boolean processPayment(double amount);

    // Méthode pour valider le paiement (peut être surchargée par les sous-classes)
    public boolean validatePayment() {
        return true; // Validation de base
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}