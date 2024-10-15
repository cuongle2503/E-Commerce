package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByPriceBetween(Double priceMin, Double priceMax);

    List<Product> findByOrderByPriceAsc();

    List<Product> findByOrderByPriceDesc();

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetweenAndBrand_NameIn(Double minPrice,
                                                    Double maxPrice,
                                                    List<String> brandNames);

    List<Product> findByPriceBetweenAndCategory_NameIn(Double minPrice,
                                                       Double maxPrice,
                                                       List<String> categoryNames);;

    List<Product> findByBrand_NameInAndCategory_NameIn(List<String> brandNames,
                                                       List<String> categoryNames);

    List<Product> findByPriceBetweenAndBrand_NameInAndCategory_NameIn(Double minPrice,
                                                                      Double maxPrice,
                                                                      List<String> brandNames,
                                                                      List<String> categoryNames);

}
