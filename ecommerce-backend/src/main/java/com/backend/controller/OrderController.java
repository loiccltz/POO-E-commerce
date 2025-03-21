package com.backend.controller;

import com.backend.entity.Order;
import com.backend.entity.User;
import com.backend.repository.OrderRepository;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;         
import com.backend.repository.UserRepository; 
import com.backend.entity.CartItem;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestHeader("Authorization") String jwt) {
        try {
            User user = userService.findUserProfileByJwt(jwt);
            
            if (user.getCart() == null || user.getCart().getItems().isEmpty()) {
                return new ResponseEntity<>("Le panier est vide", HttpStatus.BAD_REQUEST);
            }
            
            Order order = new Order();
            order.setUser(user);
            
            // Créer une nouvelle liste pour éviter la référence directe    
        // aux items du panier (qui sont embarqués)
            List<CartItem> orderItems = new ArrayList<>(user.getCart().getItems());
            order.setItems(orderItems);
            
            order.placeOrder();
            Order savedOrder = orderRepository.save(order);
            
            // Vider le panier après la commande
            user.getCart().getItems().clear();
            userRepository.save(user);  // Sauvegarder l'utilisateur avec le panier vidé
            
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) { 
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    // GET /orders/{id} - Récupérer les détails d'une commande
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Commande non trouvée", HttpStatus.NOT_FOUND);
        }
    }
}