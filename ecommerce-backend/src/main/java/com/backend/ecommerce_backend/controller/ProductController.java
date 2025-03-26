package com.backend.ecommerce_backend.controller;

import com.backend.ecommerce_backend.entity.Product;
import com.backend.ecommerce_backend.exception.ProductException;
import com.backend.ecommerce_backend.service.ProductService;
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
@RequestMapping("/api/products")
@Tag(name = "Gestion des Produits", description = "Opérations de gestion du catalogue de produits")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    @Operation(
        summary = "Créer un produit", 
        description = "Ajouter un nouveau produit au catalogue",
        responses = {
            @ApiResponse(
                responseCode = "201", 
                description = "Produit créé avec succès",
                content = @Content(schema = @Schema(implementation = Product.class))
            )
        }
    )
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Operation(
        summary = "Mettre à jour un produit", 
        description = "Modifier les détails d'un produit existant",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Produit mis à jour avec succès",
                content = @Content(schema = @Schema(implementation = Product.class))
            )
        }
    )
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductException {
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(
        summary = "Supprimer un produit", 
        description = "Retirer un produit du catalogue",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Produit supprimé avec succès"
            )
        }
    )
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Produit supprimé avec succès", HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    @Operation(
        summary = "Obtenir un produit par ID", 
        description = "Récupérer un produit spécifique à partir de son identifiant unique",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Produit récupéré avec succès",
                content = @Content(schema = @Schema(implementation = Product.class))
            )
        }
    )
    public ResponseEntity<Product> findProductById(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(
        summary = "Obtenir tous les produits", 
        description = "Récupérer l'ensemble du catalogue de produits",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Produits récupérés avec succès",
                content = @Content(schema = @Schema(implementation = List.class))
            )
        }
    )
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/name/{productName}")
    @Operation(
        summary = "Obtenir un produit par nom", 
        description = "Récupérer un produit à partir de son nom",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Produit récupéré avec succès",
                content = @Content(schema = @Schema(implementation = Product.class))
            )
        }
    )
    public ResponseEntity<Product> findProductByName(@PathVariable String productName) throws ProductException {
        Product product = productService.findProductByName(productName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}