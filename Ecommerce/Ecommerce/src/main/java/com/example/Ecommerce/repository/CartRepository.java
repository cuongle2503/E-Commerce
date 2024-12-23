package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByCustomer(Customer customer);
}
