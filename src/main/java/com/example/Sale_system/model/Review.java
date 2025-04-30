package com.example.Sale_system.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Sản phẩm được đánh giá

    @Column(nullable = false)
    private String username; // Tên người đánh giá

    @Column(nullable = false)
    private int rating; // Số sao (1-5)

    @Column(nullable = false, length = 1000)
    private String comment; // Nhận xét chi tiết

    @Column(nullable = false)
    private LocalDateTime reviewDate; // Ngày đánh giá

    // Constructor mặc định
    public Review() {}

    // Constructor đầy đủ
    public Review(Product product, String username, int rating, String comment, LocalDateTime reviewDate) {
        this.product = product;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getReviewDate() { return reviewDate; }
    public void setReviewDate(LocalDateTime reviewDate) { this.reviewDate = reviewDate; }
}
