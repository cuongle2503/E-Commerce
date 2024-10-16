package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.CartRequest;
import com.example.Ecommerce.dto.response.CartResponse;
import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.mapper.CartMapper;
import com.example.Ecommerce.repository.CartRepository;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImplement implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public CartResponse addCart(CartRequest cartRequest) {
        Customer customer = customerRepository.findById(cartRequest.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Khách hàng không tìm thấy"));

        Cart cart =  cartMapper.toCart(cartRequest);
        cart.setCustomer(customer);

        List<Product> products = productRepository.findAllById(cartRequest.getProductIds());
        cart.setProducts(products);

        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    @Override
    public List<CartResponse> getCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toCartResponse)
                .toList();
    }
}
