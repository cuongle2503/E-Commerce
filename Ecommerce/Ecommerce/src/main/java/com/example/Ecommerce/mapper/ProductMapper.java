package com.example.Ecommerce.mapper;

import com.example.Ecommerce.dto.request.ProductRequest;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "brandId", target = "brand.id")
    Product toProduct(ProductRequest request);

    @Mapping(source = "category.name", target = "category.name")
    @Mapping(source = "brand.name", target = "brand.name")
    ProductResponse toProductResponse(Product product);
}
