package com.example.Ecommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String name;
    String address;
    String telephone;
    String orderNote;
    String cartId;
}
