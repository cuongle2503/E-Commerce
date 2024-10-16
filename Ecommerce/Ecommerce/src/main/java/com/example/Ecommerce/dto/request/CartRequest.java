package com.example.Ecommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {
    Integer quantity;
    String customerId;
    List<String> productIds;
}
