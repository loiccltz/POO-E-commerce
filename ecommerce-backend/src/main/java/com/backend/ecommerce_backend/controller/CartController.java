package com.backend.ecommerce_backend.controller;

import com.backend.ecommerce_backend.entity.Cart;
import com.backend.ecommerce_backend.exception.CartException;
import com.backend.ecommerce_backend.exception.ProductException;
import com.backend.ecommerce_backend.exception.UserException;
import com.backend.ecommerce_backend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Tag(name = "Panier", description = "Route concernant le panier")
public class CartController {
    
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    @Operation(
        summary = "ajouter un produit au panier", 
        description = "ajouter un produit au panier",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "produit ajouter au panier",
                content = @Content(schema = @Schema(implementation = Cart.class))
            )
        }
    )
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
    @Operation(
        summary = "Suprrime un produit du panier", 
        description = "Suprrime un produit du panier",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Supprimé",
                content = @Content(schema = @Schema(implementation = Cart.class))
            )
        }
    )
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
    @Operation(
        summary = "Recupere le panier d'un utilisateur", 
        description = "Recupere le panier d'un utilisateur",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Recupere le panier d'un utilisateur",
                content = @Content(schema = @Schema(implementation = Cart.class))
            )
        }
    )
    public ResponseEntity<Cart> getCartByUser(@PathVariable Long userId) 
        throws UserException, CartException {
        Cart cart = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}