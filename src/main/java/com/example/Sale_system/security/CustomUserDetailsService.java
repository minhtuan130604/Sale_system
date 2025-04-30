package com.example.Sale_system.security;

import com.example.Sale_system.model.User;
import com.example.Sale_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔍 Đang tìm kiếm user: " + username);

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            System.out.println("❌ Không tìm thấy user! Đang trả về lỗi xác thực.");
            throw new UsernameNotFoundException("User không tồn tại trong database");
        }

        System.out.println("✅ User tìm thấy: " + user.get().getUsername());
        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(), user.get().getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.get().getRole()))
        );
    }

}
