package com.example.Sale_system.service;

import com.example.Sale_system.model.Product;
import com.example.Sale_system.model.Review;
import com.example.Sale_system.repository.ProductRepository;
import com.example.Sale_system.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository; // Để lấy sản phẩm từ database

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    // Lấy danh sách đánh giá của sản phẩm
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    // Gửi đánh giá mới
    public Review createReview(Long productId, Review review) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
        review.setProduct(product); // Sử dụng setProduct thay vì setProductId
        return reviewRepository.save(review);
    }
}
