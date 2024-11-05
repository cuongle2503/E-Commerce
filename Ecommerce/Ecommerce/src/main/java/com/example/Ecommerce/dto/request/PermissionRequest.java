package com.example.Ecommerce.dto.request;

import com.example.Ecommerce.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionRequest {
    String name;
    String description;

}

