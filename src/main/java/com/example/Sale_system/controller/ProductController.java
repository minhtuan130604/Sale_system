package com.example.Sale_system.controller;

import com.example.Sale_system.model.Product;
import com.example.Sale_system.model.Review;
import com.example.Sale_system.service.ProductService;
import com.example.Sale_system.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller // Chuyển từ @RestController sang @Controller để hiển thị giao diện HTML
@RequestMapping("/dashboard/products")
public class ProductController {
    private final ProductService productService;
    private final ReviewService reviewService;

    public ProductController(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String showProductPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "dashboard/products"; // Đảm bảo trả về đúng giao diện danh sách sản phẩm
    }


    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // Lấy danh sách đánh giá của sản phẩm
    @GetMapping("/{id}/reviews")
    public List<Review> getProductReviews(@PathVariable Long id) {
        return reviewService.getReviewsByProductId(id);
    }

    // Gửi đánh giá mới
    @PostMapping("/{id}/reviews")
    public Review createReview(@PathVariable Long id, @RequestBody Review review) {
        return reviewService.createReview(id, review);
    }

    @GetMapping("/filter")
    @ResponseBody // Trả về JSON thay vì HTML
    public List<Product> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sort) {

        return productService.getFilteredProducts(category, minPrice, maxPrice, sort);
    }
}