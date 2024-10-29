package com.example.Ecommerce.dto.response;

import com.example.Ecommerce.entity.CartItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    String id;
    CustomerCartResponse customer;
    List<CartItemResponse> cartItems;
    Double totalPrice;
}
