package com.example.Ecommerce.dto.response;

import com.example.Ecommerce.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String name;
    Double price;
    CategoryResponse category;
    String detail;
    String images;
    BrandResponse brand;
}
