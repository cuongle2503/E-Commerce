package com.example.Ecommerce.mapper;

import com.example.Ecommerce.dto.request.OrderRequest;
import com.example.Ecommerce.dto.response.CartResponse;
import com.example.Ecommerce.dto.response.OrderResponse;
import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "cart", ignore = true)
    Order toOrder(OrderRequest orderRequest);

    OrderResponse toOrderResponse(Order order);

    CartResponse toCartResponse(Cart cart);
}
