package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.CartRequest;
import com.example.Ecommerce.dto.response.CartResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    CartResponse addCart(CartRequest cartRequest);
    List<CartResponse> getCarts();
    void deleteProductFromCart(String customerId, String productId);
}
