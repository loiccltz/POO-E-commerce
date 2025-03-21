package com.backend.controller;

import com.backend.entity.Cart;
import com.backend.entity.Product;
import com.backend.entity.User;
import com.backend.repository.ProductRepository;
import com.backend.repository.UserRepository;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestHeader("Authorization") String jwt,
                                       @RequestParam Long productId,
                                       @RequestParam int quantity) {
        try {
            User user = userService.findUserProfileByJwt(jwt);
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            
            // Si l'utilisateur n'a pas encore de panier, en créer un
            if (user.getCart() == null) {
                Cart cart = new Cart();
                cart.setUser(user);
                user.setCart(cart);
            }
            
            user.getCart().addProduct(product, quantity);
            
            // Sauvegarder le panier après modification
            userRepository.save(user);
            
            return new ResponseEntity<>("Produit ajouté au panier", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/remove")
    public ResponseEntity<?> removeFromCart(@RequestHeader("Authorization") String jwt,
                                           @RequestParam Long productId) {
        try {
            User user = userService.findUserProfileByJwt(jwt);
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            
            if (user.getCart() != null) {
                user.getCart().removeProduct(product);
                // Sauvegarder le panier après modification
                userRepository.save(user);
                return new ResponseEntity<>("Produit retiré du panier", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Panier vide", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}