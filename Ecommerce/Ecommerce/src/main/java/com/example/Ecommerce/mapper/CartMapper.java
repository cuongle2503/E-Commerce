package com.example.Ecommerce.mapper;

import com.example.Ecommerce.dto.request.CartRequest;
import com.example.Ecommerce.dto.response.CartResponse;
import com.example.Ecommerce.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "customer", ignore = true)
    Cart toCart(CartRequest cartRequest);

    CartResponse toCartResponse(Cart cart);
}