package com.backend.ecommerce_backend.controller;

import com.backend.ecommerce_backend.entity.Order;
import com.backend.ecommerce_backend.exception.CartException;
import com.backend.ecommerce_backend.exception.OrderException;
import com.backend.ecommerce_backend.exception.UserException;
import com.backend.ecommerce_backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Commande passé", description = "OGerer les commandes passés")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    @Operation(
        summary = "Placer une commande", 
        description = "Crée une commande a partir du panier de l'utilisateur",
        responses = {
            @ApiResponse(
                responseCode = "201", 
                description = "Commande crée",
                content = @Content(schema = @Schema(implementation = Order.class))
            )
        }
    )
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrderRequest request) 
        throws UserException, CartException, OrderException {
        Order order = orderService.placeOrder(request.getUserId());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}/status")
    @Operation(
        summary = "Mettre a jour le status", 
        description = "Modifier le status d'une commande",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Status de la commande modifier",
                content = @Content(schema = @Schema(implementation = Order.class))
            )
        }
    )
    public ResponseEntity<Order> updateOrderStatus(
        @PathVariable Long orderId, 
        @RequestBody UpdateOrderStatusRequest request
    ) throws OrderException {
        Order updatedOrder = orderService.updateOrderStatus(
            orderId, 
            request.getNewStatus()
        );
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @Operation(
        summary = "Recuperer les commandes d'un utilisateur", 
        description = "Recupere les commandes d'un utilisateur spécifique",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Commande récupérer",
                content = @Content(schema = @Schema(implementation = List.class))
            )
        }
    )
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) 
        throws UserException {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    @Operation(
        summary = "Recupere une commande par l'id", 
        description = "Recupere une commande par l'id\"",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Commande recuperer",
                content = @Content(schema = @Schema(implementation = Order.class))
            )
        }
    )
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) 
        throws OrderException {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public static class PlaceOrderRequest {
        private Long userId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    public static class UpdateOrderStatusRequest {
        private Order.OrderStatus newStatus;

        public Order.OrderStatus getNewStatus() {
            return newStatus;
        }

        public void setNewStatus(Order.OrderStatus newStatus) {
            this.newStatus = newStatus;
        }
    }
}