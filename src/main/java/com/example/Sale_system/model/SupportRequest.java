package com.example.Sale_system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "support_requests")
public class SupportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // Tên người gửi yêu cầu

    @Column(nullable = false, length = 1000)
    private String message; // Nội dung yêu cầu hỗ trợ

    @Column(nullable = false)
    private String status; // Trạng thái yêu cầu (Đang xử lý, Đã phản hồi, Đã giải quyết)

    @Column(nullable = false)
    private LocalDateTime requestDate; // Ngày gửi yêu cầu

    // Constructor mặc định
    public SupportRequest() {}

    // Constructor đầy đủ
    public SupportRequest(String username, String message, String status, LocalDateTime requestDate) {
        this.username = username;
        this.message = message;
        this.status = status;
        this.requestDate = requestDate;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }
}
