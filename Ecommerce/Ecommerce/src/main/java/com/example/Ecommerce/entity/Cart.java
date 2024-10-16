package com.example.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor()
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    Integer quantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> products;
}
