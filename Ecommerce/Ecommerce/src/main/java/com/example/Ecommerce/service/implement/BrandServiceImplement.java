package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.BrandRequest;
import com.example.Ecommerce.dto.response.BrandResponse;
import com.example.Ecommerce.entity.Brand;
import com.example.Ecommerce.mapper.BrandMapper;
import com.example.Ecommerce.repository.BrandRepository;
import com.example.Ecommerce.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImplement implements BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    BrandMapper brandMapper;

    @Override
    public BrandResponse addBrand(BrandRequest brandRequest) {
        Brand brand = brandMapper.toBrand(brandRequest);
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }

    @Override
    public List<BrandResponse> getBrand() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toBrandResponse)
                .toList();
    }
}
