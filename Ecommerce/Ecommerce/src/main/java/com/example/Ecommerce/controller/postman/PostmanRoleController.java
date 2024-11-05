package com.example.Ecommerce.controller.postman;

import com.example.Ecommerce.dto.request.RoleRequest;
import com.example.Ecommerce.dto.response.RoleResponse;
import com.example.Ecommerce.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postman/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostmanRoleController {
    RoleService roleService;

    @PostMapping("/create")
    RoleResponse create(@RequestBody RoleRequest permissionRequest){
        return roleService.create(permissionRequest);
    }

    @GetMapping("/getAll")
    List<RoleResponse> getAll(){
        return roleService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") String roleId){
        roleService.delete(roleId);
    }
}
