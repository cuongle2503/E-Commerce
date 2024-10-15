package com.example.Ecommerce.mapper;

import com.example.Ecommerce.dto.request.CategoryRequest;
import com.example.Ecommerce.dto.response.CategoryResponse;
import com.example.Ecommerce.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);
}
