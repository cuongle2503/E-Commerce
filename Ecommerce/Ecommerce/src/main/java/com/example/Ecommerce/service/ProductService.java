package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.ProductRequest;
import com.example.Ecommerce.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);
    List<ProductResponse> getProducts();
    List<ProductResponse> getProductsByCategory(String category);
    List<ProductResponse> getProductsByBrand(String category);
    List<ProductResponse> findByPriceBetween(Double priceMin, Double priceMax);
    List<ProductResponse> findByOrderByPriceAsc();
    List<ProductResponse> findByOrderByPriceDesc();
    List<ProductResponse> searchProductsByName(String name);
}
