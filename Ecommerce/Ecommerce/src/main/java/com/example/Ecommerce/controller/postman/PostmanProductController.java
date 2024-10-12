package com.example.Ecommerce.controller.postman;

import com.example.Ecommerce.dto.request.ProductRequest;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postman/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostmanProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public ProductResponse addProduct(@RequestBody @Valid ProductRequest request){
        return  productService.addProduct(request);
    }

    @GetMapping("/getProducts")
    public List<ProductResponse> getProducts() {
        List<ProductResponse> productResponseList;
        productResponseList = productService.getProducts();
        return productResponseList;
    }

    @GetMapping("/getProductsByCategory/{category}")
    public List<ProductResponse> getProductsByCategory(@PathVariable("category") String category) {
        List<ProductResponse> productResponseList;
        productResponseList = productService.getProductsByCategory(category);
        return productResponseList;
    }

    @GetMapping("/getProductsByBrand/{brand}")
    public List<ProductResponse> getProductsByBrand(@PathVariable("brand") String brand) {
        List<ProductResponse> productResponseList;
        productResponseList = productService.getProductsByBrand(brand);
        return productResponseList;
    }

    @GetMapping("/getProductsByPriceRange/{priceMin}/{priceMax}")
    public List<ProductResponse> findByPriceBetween(@PathVariable("priceMin") Double priceMin, @PathVariable("priceMax") Double priceMax) {
        List<ProductResponse> productResponseList;
        productResponseList = productService.findByPriceBetween(priceMin, priceMax);
        return productResponseList;
    }


}
