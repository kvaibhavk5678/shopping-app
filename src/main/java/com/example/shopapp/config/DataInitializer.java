package com.example.shopapp.config;

import com.example.shopapp.entity.Product;
import com.example.shopapp.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {

                productRepository.save(new Product(
                        "Wireless Headphones",
                        "Over-ear Bluetooth headphones with active noise cancellation and 30-hour battery life.",
                        new BigDecimal("59.99"),
                        "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=600&q=80",
                        50
                ));

                productRepository.save(new Product(
                        "Mechanical Keyboard",
                        "Compact 75% mechanical keyboard with hot-swappable switches and RGB backlighting.",
                        new BigDecimal("89.00"),
                        "https://images.unsplash.com/photo-1587829741301-dc798b83add3?auto=format&fit=crop&w=600&q=80",
                        30
                ));

                productRepository.save(new Product(
                        "Stainless Steel Water Bottle",
                        "Insulated 1-liter bottle that keeps drinks cold for 24 hours or hot for 12.",
                        new BigDecimal("19.50"),
                        "https://images.unsplash.com/photo-1602143407151-7111542de6e8?auto=format&fit=crop&w=600&q=80",
                        100
                ));

                productRepository.save(new Product(
                        "Running Shoes",
                        "Lightweight breathable running shoes with cushioned sole, unisex sizing.",
                        new BigDecimal("74.99"),
                        "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&w=600&q=80",
                        40
                ));

                productRepository.save(new Product(
                        "4K Webcam",
                        "USB webcam with 4K resolution, autofocus and a built-in privacy shutter.",
                        new BigDecimal("64.25"),
                        "https://images.unsplash.com/photo-1587826080692-f439cd0b70da?auto=format&fit=crop&w=600&q=80",
                        25
                ));

                productRepository.save(new Product(
                        "Novel: The Silent Orchard",
                        "A bestselling mystery novel set in a small coastal town.",
                        new BigDecimal("12.99"),
                        "https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&w=600&q=80",
                        200
                ));

                productRepository.save(new Product(
                        "Smartwatch Pro",
                        "Feature-rich smartwatch with heart rate monitor, sleep tracking, and 7-day battery.",
                        new BigDecimal("129.99"),
                        "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=600&q=80",
                        35
                ));

                productRepository.save(new Product(
                        "Portable Power Bank",
                        "20000mAh ultra-compact power bank with dual USB ports and fast charging support.",
                        new BigDecimal("34.99"),
                        "https://images.unsplash.com/photo-1609091839311-d5365f9ff1c5?auto=format&fit=crop&w=600&q=80",
                        60
                ));
            }
        };
    }
}