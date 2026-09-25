package com.example.shopapp.service;

import com.example.shopapp.entity.CartItem;
import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void addToCart(User user, Product product, int quantity) {
        CartItem item = cartItemRepository.findByUserAndProduct(user, product)
                .orElse(new CartItem(user, product, 0));
        item.setQuantity(item.getQuantity() + quantity);
        cartItemRepository.save(item);
    }

    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    public BigDecimal getCartTotal(User user) {
        return cartItemRepository.findByUser(user).stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }
}
