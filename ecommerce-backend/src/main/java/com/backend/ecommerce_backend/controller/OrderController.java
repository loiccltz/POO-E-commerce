package com.backend.ecommerce_backend.controller;

import com.backend.ecommerce_backend.entity.Order;
import com.backend.ecommerce_backend.exception.CartException;
import com.backend.ecommerce_backend.exception.OrderException;
import com.backend.ecommerce_backend.exception.UserException;
import com.backend.ecommerce_backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrderRequest request) 
        throws UserException, CartException, OrderException {
        Order order = orderService.placeOrder(request.getUserId());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}/status")
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
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) 
        throws UserException {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) 
        throws OrderException {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // Classes internes pour les requêtes

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