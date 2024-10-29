package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
    Optional<CartItem> findByCartAndProduct_Id(Cart cart, String productId);

    void deleteByCartAndProduct_Id(Cart cart, String productId);
}
