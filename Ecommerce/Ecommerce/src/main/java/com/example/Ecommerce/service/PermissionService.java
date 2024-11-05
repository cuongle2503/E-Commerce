package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.PermissionRequest;
import com.example.Ecommerce.dto.response.PermissionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {
    PermissionResponse create(PermissionRequest request);
    List<PermissionResponse> getAll();
    void delete(String permission);
}
