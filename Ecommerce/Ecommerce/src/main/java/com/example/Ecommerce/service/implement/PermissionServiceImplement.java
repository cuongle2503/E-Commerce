package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.PermissionRequest;
import com.example.Ecommerce.dto.response.PermissionResponse;
import com.example.Ecommerce.entity.Permission;
import com.example.Ecommerce.mapper.PermissionMapper;
import com.example.Ecommerce.repository.PermissionRepository;
import com.example.Ecommerce.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImplement implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
