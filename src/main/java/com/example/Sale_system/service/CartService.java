package com.example.Sale_system.service;

import com.example.Sale_system.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private List<CartItem> cart = new ArrayList<>();

    public void addToCart(CartItem item) {
        cart.add(item);
    }

    public void removeFromCart(Long id) {
        cart.removeIf(item -> item.getId().equals(id));
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public double getTotalPrice() {
        return cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
