package com.backend.ecommerce_backend.service;

import com.backend.ecommerce_backend.entity.Cart;
import com.backend.ecommerce_backend.entity.Order;
import com.backend.ecommerce_backend.entity.User;
import com.backend.ecommerce_backend.exception.CartException;
import com.backend.ecommerce_backend.exception.OrderException;
import com.backend.ecommerce_backend.exception.UserException;
import com.backend.ecommerce_backend.repository.CartRepository;
import com.backend.ecommerce_backend.repository.OrderRepository;
import com.backend.ecommerce_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, 
                        UserRepository userRepository,
                        CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public Order placeOrder(Long userId) throws UserException, CartException, OrderException {
        // Find user
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserException("User not found"));

        // Find cart
        Cart cart = cartRepository.findByUser(user);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new CartException("Cart is empty");
        }

        // Create new order
        Order order = new Order();
        order.setUser(user);

        // Place order and update stock
        order.placeOrder(cart.getItems());

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Clear the cart
        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, Order.OrderStatus newStatus) throws OrderException {
        // Find order
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderException("Order not found"));

        // Update order status
        order.updateStatus(newStatus);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) throws UserException {
        // Find user
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserException("User not found"));

        // Find orders
        return orderRepository.findByUser(user);
    }

    public Order getOrderById(Long orderId) throws OrderException {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderException("Order not found"));
    }
}