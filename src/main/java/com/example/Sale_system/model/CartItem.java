package com.example.Sale_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity // Đảm bảo Hibernate nhận diện đây là một thực thể
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tạo ID tự động
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Liên kết với sản phẩm
    @JoinColumn(name = "product_id") // Định rõ khóa ngoại
    private Product product;

    @Column(nullable = false)
    private int quantity;

    // Constructor mặc định
    public CartItem() {}

    // Constructor đầy đủ
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Phương thức tính tổng giá trị của sản phẩm trong giỏ hàng
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
