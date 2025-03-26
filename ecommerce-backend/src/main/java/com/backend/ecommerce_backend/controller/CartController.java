package com.backend.ecommerce_backend.controller;

import com.backend.ecommerce_backend.entity.Cart;
import com.backend.ecommerce_backend.exception.CartException;
import com.backend.ecommerce_backend.exception.ProductException;
import com.backend.ecommerce_backend.exception.UserException;
import com.backend.ecommerce_backend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// DTO pour ajouter un produit au panier
class AddToCartRequest {
    private Long userId;
    private Long productId;
    private int quantity;

    // Getters et Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(
        @RequestBody AddToCartRequest request
    ) throws UserException, ProductException, CartException {
        Cart updatedCart = cartService.addProductToCart(
            request.getUserId(), 
            request.getProductId(), 
            request.getQuantity()
        );
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeProductFromCart(
        @RequestBody AddToCartRequest request
    ) throws UserException, ProductException, CartException {
        Cart updatedCart = cartService.removeProductFromCart(
            request.getUserId(), 
            request.getProductId()
        );
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUser(@PathVariable Long userId) 
        throws UserException, CartException {
        Cart cart = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}