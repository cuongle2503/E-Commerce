package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.ProductRequest;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.mapper.ProductMapper;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplement implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImplement.class);
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

    @Override
    public List<ProductResponse> filterProducts(Double minPrice,
                                                Double maxPrice,
                                                List<String> brandNames,
                                                List<String> categoryNames) {
        List<Product> products;
        if (minPrice != null && maxPrice != null) {
            if (brandNames != null && !brandNames.isEmpty() && categoryNames != null && !categoryNames.isEmpty()) {
                products = productRepository.findByPriceBetweenAndBrand_NameInAndCategory_NameIn(minPrice, maxPrice, brandNames, categoryNames);
            } else if (brandNames != null && !brandNames.isEmpty()) {
                products = productRepository.findByPriceBetweenAndBrand_NameIn(minPrice, maxPrice, brandNames);
            } else if (categoryNames != null && !categoryNames.isEmpty()) {
                products = productRepository.findByPriceBetweenAndCategory_NameIn(minPrice, maxPrice, categoryNames);
            } else {
                products = productRepository.findByPriceBetween(minPrice, maxPrice);
            }
        } else if (brandNames != null && !brandNames.isEmpty() && categoryNames != null && !categoryNames.isEmpty()) {
            products = productRepository.findByBrand_NameInAndCategory_NameIn(brandNames, categoryNames);
        } else {
            products = productRepository.findAll();
        }
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sản phẩm không tìm thấy với ID: " + id));
        return productMapper.toProductResponse(product);
    }


}
