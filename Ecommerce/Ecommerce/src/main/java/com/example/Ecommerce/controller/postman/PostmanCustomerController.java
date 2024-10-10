package com.example.Ecommerce.controller.postman;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.service.CustomerService;
import com.example.Ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postman/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostmanCustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;

    //    -----------------REGISTRATION---------------------
    @PostMapping("/register")
    public CustomerResponse processRegister(@Valid @RequestBody CustomerRequest request){
        CustomerResponse customerResponse = customerService.createAccount(request);
        return customerResponse;
    }
    //    --------------------------------------------------

    //    -----------------LOGIN----------------------------
    @PostMapping("/login")
    public String processLogin(@Valid @RequestBody CustomerRequest request) {
        var token = customerService.signIn(request.getEmail(), request.getPassword());
        return token;
    }
    //    --------------------------------------------------
}
