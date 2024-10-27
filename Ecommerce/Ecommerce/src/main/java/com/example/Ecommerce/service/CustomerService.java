package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.request.IntrospectRequest;
import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface CustomerService {
    CustomerResponse createAccount(CustomerRequest request);
    String signIn(String email, String password);
    String generateToken(String email);
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
}