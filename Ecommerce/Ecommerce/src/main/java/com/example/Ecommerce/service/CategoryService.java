package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.CategoryRequest;
import com.example.Ecommerce.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getCategory();
}
