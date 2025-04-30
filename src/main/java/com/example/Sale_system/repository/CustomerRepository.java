package com.example.Sale_system.repository;

import com.example.Sale_system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Có thể thêm các truy vấn tùy chỉnh nếu cần
}

