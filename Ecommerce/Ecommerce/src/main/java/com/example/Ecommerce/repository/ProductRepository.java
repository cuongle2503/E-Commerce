package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByCategory_Name(String categoryName);

    List<Product> findByBrand_Name(String category);

    List<Product> findByPriceBetween(Double priceMin, Double priceMax);

    List<Product> findByOrderByPriceAsc();

    List<Product> findByOrderByPriceDesc();

    List<Product> findByNameContainingIgnoreCase(String name);

}
