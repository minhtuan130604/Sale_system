package com.example.Sale_system.service;

import com.example.Sale_system.model.User;
import com.example.Sale_system.model.Customer; // Thêm Customer
import com.example.Sale_system.repository.UserRepository;
import com.example.Sale_system.repository.CustomerRepository; // Thêm CustomerRepository
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository; // Thêm CustomerRepository
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository; // Khởi tạo CustomerRepository
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        // Kiểm tra thông tin bắt buộc
        if (user.getUsername() == null || user.getPassword() == null || user.getName() == null ||
                user.getEmail() == null || user.getPhone() == null) {
            throw new RuntimeException("Thiếu thông tin cần thiết!");
        }

        // Kiểm tra nếu tài khoản đã tồn tại
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Tài khoản đã tồn tại. Vui lòng chọn tên khác!");
        }

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_CUSTOMER"); // Gán quyền mặc định

        // Lưu user vào database
        User savedUser = userRepository.save(user);
        System.out.println("User đã được lưu vào database: " + savedUser.getUsername());

        // Tạo customer liên kết với user
        Customer customer = new Customer();
        customer.setName(user.getName());
        customer.setEmail(user.getEmail());
        customer.setPhone(user.getPhone());
        customer.setCompany(user.getCompany());
        customer.setUser(savedUser);

        customerRepository.save(customer);
        System.out.println("Customer đã được lưu vào database: " + customer.getName());

        // Tự động đăng nhập sau khi đăng ký
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getUsername(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("User đã được tự động đăng nhập: " + savedUser.getUsername());

        return savedUser;
    }


    public Optional<User> findByUsername(String username) {
        System.out.println("Tìm kiếm user: " + username);
        Optional<User> user = userRepository.findByUsername(username);
        System.out.println("Kết quả tìm thấy: " + user.isPresent());
        return user;
    }

}
