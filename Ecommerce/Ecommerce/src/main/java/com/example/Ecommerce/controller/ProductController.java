package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.request.CartRequest;
import com.example.Ecommerce.dto.response.BrandResponse;
import com.example.Ecommerce.dto.response.CategoryResponse;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.service.BrandService;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.CategoryService;
import com.example.Ecommerce.service.ProductService;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;
    @Autowired
    CartService cartService;

    @GetMapping("/products")
    public String showProductPage(@RequestParam(value = "sort", required = false, defaultValue = "0") String sort,
                                  Model model,
                                  HttpSession session) {
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

        String jwtToken = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", jwtToken);
        model.addAttribute("isLoggedIn", jwtToken != null);

        return "customer/home/store";
    }

    @GetMapping("/products/filterProduct")
    public String filterProducts(@RequestParam(required = false) Double priceMin,
                                 @RequestParam(required = false) Double priceMax,
                                 @RequestParam(required = false) List<String> brands,
                                 @RequestParam(required = false) List<String> categories,
                                 Model model,
                                 HttpSession session) {
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

        String jwtToken = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", jwtToken);
        model.addAttribute("isLoggedIn", jwtToken != null);

        return "customer/home/filter";
    }

    @PostMapping("/adds")
    public String addToCart(@Valid CartRequest cartRequest,
                            HttpSession session) {

        String jwtToken = (String) session.getAttribute("jwtToken");

        if (jwtToken == null) {
            return "redirect:/login";
        }

        try {
            SignedJWT signedJWT = SignedJWT.parse(jwtToken);
            String customerId = (String) signedJWT.getJWTClaimsSet().getClaim("customerId");

            if (customerId == null || customerId.isEmpty()) {
                return "redirect:/login";
            }

            // Đặt customer ID vào CartRequest trước khi gọi tầng service
            cartRequest.setCustomerId(customerId);

            // Gọi service để thêm giỏ hàng
            cartService.addCart(cartRequest);
            return "redirect:/products";
        } catch (ParseException e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/detailProduct")
    public String detailProduct( @RequestParam String productId,
                                 Model model,
                                 HttpSession session) {
        String jwtToken = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", jwtToken);
        model.addAttribute("isLoggedIn", jwtToken != null);

        ProductResponse productResponse = productService.getProductById(productId);
        model.addAttribute("product", productResponse);
        return "customer/home/product";
    }
}
