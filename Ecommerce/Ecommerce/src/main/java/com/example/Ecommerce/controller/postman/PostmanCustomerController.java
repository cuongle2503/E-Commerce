package com.example.Ecommerce.controller.postman;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.request.IntrospectRequest;
import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.dto.response.IntrospectResponse;
import com.example.Ecommerce.service.CustomerService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/postman/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostmanCustomerController {
    @Autowired
    CustomerService customerService;

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

    //    -----------------INTROSPECT-----------------------
    @PostMapping("/authenticate")
    public IntrospectResponse authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        return customerService.introspect(request);
    }
    //    --------------------------------------------------


}
