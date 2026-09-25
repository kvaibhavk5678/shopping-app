package com.example.shopapp.controller;

import com.example.shopapp.entity.Product;
import com.example.shopapp.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    /**
     * Show add product form
     */
    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    /**
     * Handle form submission to add a new product
     */
    @PostMapping("/products/add")
    public String addProduct(
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam BigDecimal price,
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false, defaultValue = "0") Integer stock,
            Model model) {

        // Validation
        if (name == null || name.isBlank()) {
            model.addAttribute("error", "Product name is required");
            return "add-product";
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            model.addAttribute("error", "Price must be greater than 0");
            return "add-product";
        }

        // Create and save product
        Product product = new Product(name, description, price, imageUrl, stock);
        productRepository.save(product);

        return "redirect:/products?success=true";
    }
}
