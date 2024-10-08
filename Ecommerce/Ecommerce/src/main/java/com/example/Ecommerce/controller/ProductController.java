package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.response.ProductResponse;
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

//    @GetMapping("/homepage/product")
//    public String getProducts(@RequestParam(value = "category", required = false) String category, Model model) {
//        List<ProductResponse> products;
//
//        // Nếu có category, lấy sản phẩm theo category
//        if (category != null) {
//            products = productService.getProductsByCategory(category); // Phương thức này sẽ được tạo trong ProductService
//        } else {
//            products = productService.getProducts(); // Lấy tất cả sản phẩm nếu không có category
//        }
//        model.addAttribute("products", products);
//        return "customer/home/index"; // Trả về trang view chứa sản phẩm
//    }



}
