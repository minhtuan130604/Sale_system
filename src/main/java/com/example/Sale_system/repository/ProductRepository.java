package com.example.Sale_system.repository;

import com.example.Sale_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category); // Bây giờ phương thức này sẽ hoạt động đúng

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findAllByOrderByPriceAsc();

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findByNameContaining(String keyword);
}
