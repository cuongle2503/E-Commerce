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

        model.addAttribute("products", products);
        model.addAttribute("sort", sort);
        return "customer/home/store";
    }
}
