package com.example.Ecommerce.controller.postman;

import com.example.Ecommerce.dto.request.BrandRequest;
import com.example.Ecommerce.dto.response.BrandResponse;
import com.example.Ecommerce.service.BrandService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postman/brand")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostmanBrandController {
    @Autowired
    BrandService brandService;

    @PostMapping("/addBrand")
    public BrandResponse addCategory(@RequestBody @Valid BrandRequest request){
        return brandService.addBrand(request);
    }

    @GetMapping("/getBrand")
    public List<BrandResponse> getCategory(){
        List<BrandResponse> brandResponseList = brandService.getBrand();
        return brandResponseList;
    }
}
