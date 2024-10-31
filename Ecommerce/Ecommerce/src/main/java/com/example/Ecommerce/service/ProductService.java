package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.ProductRequest;
import com.example.Ecommerce.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);
    List<ProductResponse> getProducts();
    List<ProductResponse> findByOrderByPriceAsc();
    List<ProductResponse> findByOrderByPriceDesc();
    List<ProductResponse> searchProductsByName(String name);
    List<ProductResponse> filterProducts(Double minPrice,
                                         Double maxPrice,
                                         List<String> brandNames,
                                         List<String> categoryNames);
    ProductResponse getProductById(String id);
}
