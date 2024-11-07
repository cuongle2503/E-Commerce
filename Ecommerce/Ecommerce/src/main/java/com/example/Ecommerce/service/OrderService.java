package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.OrderRequest;
import com.example.Ecommerce.dto.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderResponse saveOrder(OrderRequest orderRequest);
    List<OrderResponse> getOrders();
}
