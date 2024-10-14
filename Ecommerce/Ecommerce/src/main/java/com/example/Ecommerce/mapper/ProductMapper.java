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

    @Mapping(source = "category.id", target = "category.id")
    @Mapping(source = "category.name", target = "category.name")
    @Mapping(source = "category.detail", target = "category.detail")
    @Mapping(source = "brand.id", target = "brand.id")
    @Mapping(source = "brand.name", target = "brand.name")
    @Mapping(source = "brand.detail", target = "brand.detail")
    ProductResponse toProductResponse(Product product);
}
