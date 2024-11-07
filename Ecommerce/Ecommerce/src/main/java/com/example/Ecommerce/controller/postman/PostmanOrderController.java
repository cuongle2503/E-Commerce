package com.example.Ecommerce.controller.postman;

import com.example.Ecommerce.dto.request.OrderRequest;
import com.example.Ecommerce.dto.response.OrderResponse;
import com.example.Ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postman/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostmanOrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return orderService.saveOrder(orderRequest);
    }

    @GetMapping("/get")
    List<OrderResponse> getOrders(){
        return orderService.getOrders();
    }
}
