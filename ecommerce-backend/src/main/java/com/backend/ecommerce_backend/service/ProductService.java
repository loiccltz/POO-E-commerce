package com.backend.ecommerce_backend.service;

import com.backend.ecommerce_backend.entity.Product;
import com.backend.ecommerce_backend.exception.ProductException;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product) throws ProductException;
    void deleteProduct(Long productId) throws ProductException;
    Product findProductById(Long productId) throws ProductException;
    List<Product> findAllProducts();
    Product findProductByName(String productName) throws ProductException;
}