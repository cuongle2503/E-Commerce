package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerResponse createAccount(CustomerRequest request);
    String signIn(String email, String password);
    String generateToken(String email);
}