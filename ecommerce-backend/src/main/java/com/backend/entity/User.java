package com.backend.entity;

import jakarta.persistence.*;

// permet de l'inscrire dans la bdd
@Entity
///
public class User {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    private String password;
    
    public String role;


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

    
}
