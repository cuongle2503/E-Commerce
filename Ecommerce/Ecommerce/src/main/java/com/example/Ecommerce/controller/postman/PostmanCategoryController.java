package com.example.Ecommerce.controller.postman;

import com.example.Ecommerce.dto.request.CategoryRequest;
import com.example.Ecommerce.dto.request.ProductRequest;
import com.example.Ecommerce.dto.response.CategoryResponse;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.service.CategoryService;
import com.example.Ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postman/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostmanCategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/addCategory")
    public CategoryResponse addCategory(@RequestBody @Valid CategoryRequest request){
        return categoryService.addCategory(request);
    }

    @GetMapping("/getCategory")
    public List<CategoryResponse> getCategory(){
        List<CategoryResponse> categoryResponseList;
        categoryResponseList = categoryService.getCategory();
        return categoryResponseList;
    }
}
