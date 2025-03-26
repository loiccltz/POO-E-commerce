package com.backend.ecommerce_backend.service;

import com.backend.ecommerce_backend.entity.Product;
import com.backend.ecommerce_backend.exception.ProductException;
import com.backend.ecommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {
    
    private final ProductRepository productRepository;

    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) throws ProductException {
        Optional<Product> existingProduct = productRepository.findById(product.getProductID());
        
        if (existingProduct.isEmpty()) {
            throw new ProductException("Le produit avec cette id n'a pas été trouvé " + product.getProductID());
        }
        
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) throws ProductException {
        Optional<Product> existingProduct = productRepository.findById(productId);
        
        if (existingProduct.isEmpty()) {
            throw new ProductException("Produit avec cette ID non trouvé : " + productId);
        }
        
        productRepository.deleteById(productId);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        return productRepository.findById(productId)
            .orElseThrow(() -> new ProductException("Produit avec cette id non trouvé: " + productId));
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductByName(String productName) throws ProductException {
        Product product = productRepository.findByProductName(productName);
        
        if (product == null) {
            throw new ProductException("Produit avec ce nom non trouvé: " + productName);
        }
        
        return product;
    }
}