package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.RoleRequest;
import com.example.Ecommerce.dto.response.RoleResponse;
import com.example.Ecommerce.entity.Role;
import com.example.Ecommerce.mapper.RoleMapper;
import com.example.Ecommerce.repository.PermissionRepository;
import com.example.Ecommerce.repository.RoleRepository;
import com.example.Ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImplement implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionRepository permissionRepository;
    @Override
    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));


        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public List<RoleResponse> getAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
