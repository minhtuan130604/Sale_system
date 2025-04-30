package com.example.Sale_system.controller;

import com.example.Sale_system.model.User;
import com.example.Sale_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/customer-dashboard")
public class CustomerDashboardController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showDashboard(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent() && "ROLE_CUSTOMER".equals(user.get().getRole())) {
            model.addAttribute("user", user.get());
            System.out.println("✅ Đã tìm thấy user: " + user.get().getName());
            return "dashboard/customer-dashboard";

        } else {
            System.out.println("❌ User không có quyền truy cập.");
            return "redirect:/access-denied";
        }
    }

}
