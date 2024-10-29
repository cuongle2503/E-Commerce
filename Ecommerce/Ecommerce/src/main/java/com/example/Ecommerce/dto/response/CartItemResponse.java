package com.example.Ecommerce.dto.response;

import com.example.Ecommerce.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    ProductCartResponse product;
    Integer quantity;
}
