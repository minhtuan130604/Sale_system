package com.example.Sale_system.controller;

import com.example.Sale_system.model.User;
import com.example.Sale_system.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Đăng ký người dùng
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);  // gọi service để đăng ký người dùng
            return "redirect:/auth/login";   // chuyển đến trang đăng nhập sau khi đăng ký thành công
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";  // trả về lại trang đăng ký nếu có lỗi
        }
    }

    // Lấy thông tin người dùng theo tên đăng nhập
    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
    }

    // Kiểm tra trạng thái đăng nhập của người dùng
    @GetMapping("/auth/status")
    public ResponseEntity<?> checkLoginStatus(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("User is authenticated as: " + authentication.getName());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated.");
    }

    // Hiển thị trang đăng nhập và xử lý lỗi từ session nếu có
    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object errorMessage = session.getAttribute("errorMessage");
            if (errorMessage != null) {
                model.addAttribute("errorMessage", errorMessage.toString());
                session.removeAttribute("errorMessage"); // clear lỗi sau khi load
            }
        }
        return "login";
    }


    // Hiển thị trang đăng ký
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // trả về trang register
    }
}
