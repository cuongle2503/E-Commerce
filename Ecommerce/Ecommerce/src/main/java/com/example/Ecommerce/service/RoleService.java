package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.PermissionRequest;
import com.example.Ecommerce.dto.request.RoleRequest;
import com.example.Ecommerce.dto.response.PermissionResponse;
import com.example.Ecommerce.dto.response.RoleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    RoleResponse create(RoleRequest request);
    List<RoleResponse> getAll();
    void delete(String role);
}
