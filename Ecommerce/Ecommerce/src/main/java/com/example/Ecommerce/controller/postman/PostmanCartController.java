package com.example.Ecommerce.controller.postman;

import com.example.Ecommerce.dto.request.CartRequest;
import com.example.Ecommerce.dto.response.CartResponse;
import com.example.Ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postman/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostmanCartController {
    @Autowired
    CartService cartService;

    @PostMapping("/addCart")
    public CartResponse addCart(@RequestBody @Valid CartRequest request){
        return cartService.addCart(request);
    }

    @GetMapping("/getCarts")
    public List<CartResponse> getCarts(){
        List<CartResponse> cartResponses = cartService.getCarts();
        return cartResponses;
    }

    @GetMapping("/deleteProduct/{customerId}/{productId}")
    public void removeProductFromCart(
            @PathVariable String customerId,
            @PathVariable String productId) {

        cartService.deleteProductFromCart(customerId, productId);
    }
}
