package com.example.Ecommerce.controller.postman;

import com.example.Ecommerce.dto.request.PermissionRequest;
import com.example.Ecommerce.dto.response.PermissionResponse;
import com.example.Ecommerce.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postman/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostmanPermissionController {
    PermissionService permissionService;

    @PostMapping("/create")
    PermissionResponse create(@RequestBody PermissionRequest permissionRequest){
        return permissionService.create(permissionRequest);
    }

    @GetMapping("/getAll")
    List<PermissionResponse> getAll(){
        return permissionService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") String permissionId){
        permissionService.delete(permissionId);
    }
}
