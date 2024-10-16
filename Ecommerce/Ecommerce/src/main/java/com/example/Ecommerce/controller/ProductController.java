package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.response.BrandResponse;
import com.example.Ecommerce.dto.response.CategoryResponse;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.service.BrandService;
import com.example.Ecommerce.service.CategoryService;
import com.example.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;

    @GetMapping("/products")
    public String showProductPage(@RequestParam(value = "sort", required = false, defaultValue = "0") String sort,
                                  Model model) {
        List<ProductResponse> products;

        if ("1".equals(sort)) {
            products = productService.findByOrderByPriceAsc();
        } else if ("2".equals(sort)) {
            products = productService.findByOrderByPriceDesc();
        } else {
            products = productService.getProducts();
        }

        List<CategoryResponse> categoryResponseList;
        categoryResponseList = categoryService.getCategory();

        List<BrandResponse> brandResponseList;
        brandResponseList = brandService.getBrand();

        model.addAttribute("products", products);
        model.addAttribute("sort", sort);

        model.addAttribute("categories", categoryResponseList);
        model.addAttribute("brands", brandResponseList);
        return "customer/home/store";
    }

    @GetMapping("/products/filterProduct")
    public String filterProducts(@RequestParam(required = false) Double priceMin,
                                 @RequestParam(required = false) Double priceMax,
                                 @RequestParam(required = false) List<String> brands,
                                 @RequestParam(required = false) List<String> categories,
                                 Model model) {
        List<ProductResponse> productResponseList;
        if (brands != null && brands.contains("null")) {
            brands = null;
        }
        if (categories != null && categories.contains("null")) {
            categories = null;
        }

        productResponseList = productService.filterProducts(priceMin, priceMax, brands, categories);
        model.addAttribute("productsFilter", productResponseList);


        List<CategoryResponse> categoryResponseList;
        categoryResponseList = categoryService.getCategory();
        model.addAttribute("categories", categoryResponseList);


        List<BrandResponse> brandResponseList;
        brandResponseList = brandService.getBrand();
        model.addAttribute("brands", brandResponseList);

        return "customer/home/filter";
    }
}
