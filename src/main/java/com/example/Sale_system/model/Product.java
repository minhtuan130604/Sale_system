package com.example.Sale_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private Double price;

    private String imageUrl; // Thêm đường dẫn hình ảnh sản phẩm

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews; // Danh sách đánh giá của sản phẩm


    @Column(nullable = false)
    private String category; // Thêm thuộc tính category để lọc theo danh mục

}
