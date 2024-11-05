package com.example.Ecommerce.mapper;

import com.example.Ecommerce.dto.request.PermissionRequest;
import com.example.Ecommerce.dto.response.PermissionResponse;
import com.example.Ecommerce.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);
}
