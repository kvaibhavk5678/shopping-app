package com.example.shopapp.controller;

import com.example.shopapp.entity.Product;
import com.example.shopapp.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    private final ProductRepository productRepository;

    public ProductApiController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get all products
     * GET /api/products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    /**
     * Get product by ID
     * GET /api/products/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add a new product
     * POST /api/products
     *
     * Request body example:
     * {
     *   "name": "Product Name",
     *   "description": "Product description",
     *   "price": 29.99,
     *   "imageUrl": "https://example.com/image.jpg",
     *   "stock": 50
     * }
     */
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest request) {
        // Validate required fields
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getImageUrl(),
                request.getStock() != null ? request.getStock() : 0
        );

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    /**
     * Update an existing product
     * PUT /api/products/{id}
     *
     * Request body example:
     * {
     *   "name": "Updated Product Name",
     *   "description": "Updated description",
     *   "price": 39.99,
     *   "imageUrl": "https://example.com/new-image.jpg",
     *   "stock": 75
     * }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = optionalProduct.get();

        // Update fields if provided
        if (request.getName() != null && !request.getName().isBlank()) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            product.setPrice(request.getPrice());
        }
        if (request.getImageUrl() != null) {
            product.setImageUrl(request.getImageUrl());
        }
        if (request.getStock() != null) {
            product.setStock(request.getStock());
        }

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Delete a product
     * DELETE /api/products/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * DTO for Product requests
     */
    public static class ProductRequest {
        private String name;
        private String description;
        private BigDecimal price;
        private String imageUrl;
        private Integer stock;

        // Constructors
        public ProductRequest() {
        }

        public ProductRequest(String name, String description, BigDecimal price, String imageUrl, Integer stock) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.imageUrl = imageUrl;
            this.stock = stock;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }
    }
}

