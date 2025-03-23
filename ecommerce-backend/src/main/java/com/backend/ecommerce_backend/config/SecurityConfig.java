package com.backend.ecommerce_backend.config; // Doit correspondre au package réel

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Création du bean PasswordEncoder"); // Pour vérification
        return new BCryptPasswordEncoder();
    }
}