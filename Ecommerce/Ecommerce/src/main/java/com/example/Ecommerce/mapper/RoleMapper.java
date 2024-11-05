package com.example.Ecommerce.mapper;

import com.example.Ecommerce.dto.request.PermissionRequest;
import com.example.Ecommerce.dto.request.RoleRequest;
import com.example.Ecommerce.dto.response.PermissionResponse;
import com.example.Ecommerce.dto.response.RoleResponse;
import com.example.Ecommerce.entity.Permission;
import com.example.Ecommerce.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);
    RoleResponse toRoleResponse(Role role);
}
