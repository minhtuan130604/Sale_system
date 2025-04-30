package com.example.Sale_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Sale_system.service.CartService;

@Controller
@RequestMapping("/dashboard")
public class CartController {

    @Autowired
    private CartService cartService; // Thêm service giỏ hàng

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "dashboard/cart";
    }
}
