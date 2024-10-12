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

    @GetMapping("/store")
    public String showProductPage(Model model) {
        return "customer/home/store";
    }

}
