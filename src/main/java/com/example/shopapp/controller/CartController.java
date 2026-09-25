package com.example.shopapp.controller;

import com.example.shopapp.entity.User;
import com.example.shopapp.repository.UserRepository;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;

    public CartController(UserRepository userRepository, CartService cartService, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    @GetMapping("/cart")
    public String viewCart(Model model, @AuthenticationPrincipal UserDetails principal) {
        User user = currentUser(principal);
        model.addAttribute("cartItems", cartService.getCartItems(user));
        model.addAttribute("total", cartService.getCartTotal(user));
        return "cart";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam(defaultValue = "1") int quantity,
                            @AuthenticationPrincipal UserDetails principal) {
        User user = currentUser(principal);
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        cartService.addToCart(user, product, quantity);
        return "redirect:/products?added=true";
    }

    @PostMapping("/cart/remove/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }

    private User currentUser(UserDetails principal) {
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found in database"));
    }
}
