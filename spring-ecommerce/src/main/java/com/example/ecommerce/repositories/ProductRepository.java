package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.ecommerce.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p from Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Product> searchProducts(String keyword);
}
