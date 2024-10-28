package com.example.Ecommerce.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    String id;
    String name;
    String email;
    String phoneNumber;
    String address;
    Set<String> roles;
}