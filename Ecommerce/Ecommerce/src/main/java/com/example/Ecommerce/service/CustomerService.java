package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.request.IntrospectRequest;
import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.dto.response.IntrospectResponse;
import com.example.Ecommerce.entity.Customer;
import com.nimbusds.jose.JOSEException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface CustomerService {
    CustomerResponse createAccount(CustomerRequest request);
    List<CustomerResponse> getCustomers();
    String signIn(String email, String password);
    String generateToken(Customer customer);
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
    CustomerResponse getCustomer(String id);
    CustomerResponse getMyInfo();
}