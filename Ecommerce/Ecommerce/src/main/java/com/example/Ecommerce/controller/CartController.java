package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.request.CartRequest;
import com.example.Ecommerce.dto.response.CartResponse;
import com.example.Ecommerce.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CartController {
//    @Autowired
//    private CartService cartService;
//
//    @PostMapping("/add")
//    public String addToCart(@RequestBody CartRequest cartRequest,
//                            HttpSession session) {
//        CartResponse cartResponse = cartService.addCart(cartRequest);
//        return "customer/home/index";
//    }
}
