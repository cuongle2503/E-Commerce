package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.ProductRequest;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.mapper.ProductMapper;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public List<ProductResponse> getProductsByBrand(String brand) {
        List<Product> products = productRepository.findByBrand(brand);
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public List<ProductResponse> findByPriceBetween(Double priceMin, Double priceMax) {
        List<Product> products = productRepository.findByPriceBetween(priceMin, priceMax);
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public List<ProductResponse> findByOrderByPriceAsc() {
        List<Product> products = productRepository.findByOrderByPriceAsc();
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public List<ProductResponse> findByOrderByPriceDesc() {
        List<Product> products = productRepository.findByOrderByPriceDesc();
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public List<ProductResponse> searchProductsByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream().map(productMapper::toProductResponse).toList();
    }


}
