package com.example.Ecommerce.mapper;

import com.example.Ecommerce.dto.request.BrandRequest;
import com.example.Ecommerce.dto.response.BrandResponse;
import com.example.Ecommerce.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toBrand(BrandRequest request);
    BrandResponse toBrandResponse(Brand brand);
}
