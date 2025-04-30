package com.example.Sale_system.service;

import com.example.Sale_system.model.Product;
import com.example.Sale_system.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Lấy danh sách sản phẩm với bộ lọc đầy đủ
    public List<Product> getFilteredProducts(String category, Double minPrice, Double maxPrice, String sort) {
        List<Product> filteredProducts;

        if (category != null && !category.isEmpty()) {
            filteredProducts = productRepository.findByCategory(category);
        } else {
            filteredProducts = productRepository.findAll();
        }

        if (minPrice != null && maxPrice != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                    .toList();
        }

        if ("asc".equalsIgnoreCase(sort)) {
            filteredProducts = filteredProducts.stream()
                    .sorted(Comparator.comparing(Product::getPrice))
                    .toList();
        } else if ("desc".equalsIgnoreCase(sort)) {
            filteredProducts = filteredProducts.stream()
                    .sorted(Comparator.comparing(Product::getPrice).reversed())
                    .toList();
        }

        return filteredProducts;
    }
}
