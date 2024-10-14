package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.request.BrandRequest;
import com.example.Ecommerce.dto.response.BrandResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    BrandResponse addBrand(BrandRequest brandRequest);
    List<BrandResponse> getBrand();
}
