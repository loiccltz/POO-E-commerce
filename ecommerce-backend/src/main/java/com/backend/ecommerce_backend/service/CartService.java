package com.backend.ecommerce_backend.service;

import com.backend.ecommerce_backend.entity.Cart;
import com.backend.ecommerce_backend.entity.Product;
import com.backend.ecommerce_backend.entity.User;
import com.backend.ecommerce_backend.exception.CartException;
import com.backend.ecommerce_backend.exception.ProductException;
import com.backend.ecommerce_backend.exception.UserException;
import com.backend.ecommerce_backend.repository.CartRepository;
import com.backend.ecommerce_backend.repository.ProductRepository;
import com.backend.ecommerce_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, 
                       UserRepository userRepository, 
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Cart createCart(User user) {
        Cart cart = new Cart(user);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart addProductToCart(Long userId, Long productId, int quantity) throws UserException, ProductException, CartException {
        // Find user
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserException("User not found"));

        // Find product
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductException("Product not found"));

        // Check if product is in stock
        if (product.getStockQuantity() < quantity) {
            throw new CartException("Insufficient stock");
        }

        // Find or create cart for user
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart(user);
        }

        // Add product to cart
        cart.addProduct(product, quantity);

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeProductFromCart(Long userId, Long productId) throws UserException, ProductException, CartException {
        // Find user
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserException("User not found"));

        // Find product
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductException("Product not found"));

        // Find cart
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            throw new CartException("Cart not found");
        }

        // Remove product from cart
        cart.removeProduct(product);

        return cartRepository.save(cart);
    }

    public Cart getCartByUser(Long userId) throws UserException, CartException {
        // Find user
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserException("User not found"));

        // Find cart
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            throw new CartException("Cart not found");
        }

        return cart;
    }
}