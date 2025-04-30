package com.example.Sale_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "orders") // Đặt tên bảng trong cơ sở dữ liệu
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId; // ID của người dùng đặt hàng

    @Column(nullable = false)
    private String status; // Trạng thái đơn hàng (Chờ xác nhận, Đang giao, Đã nhận)

    @Column(nullable = false)
    private LocalDateTime orderDate; // Ngày đặt hàng

    private LocalDateTime deliveryDate; // Ngày giao hàng dự kiến

    private boolean completed; // Đã hoàn thành hay chưa

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // Liên kết với bảng CartItem
    private List<CartItem> cartItems;

    private Double totalPrice; // Tổng tiền cần thanh toán
    private String paymentMethod; // Phương thức thanh toán (Ví điện tử, Thẻ, COD)

    // Constructor mặc định
    public Order() {}

    // Constructor đầy đủ
    public Order(String userId, String status, LocalDateTime orderDate, LocalDateTime deliveryDate,
                 boolean completed, List<CartItem> cartItems, Double totalPrice, String paymentMethod) {
        this.userId = userId;
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.completed = completed;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }
}
