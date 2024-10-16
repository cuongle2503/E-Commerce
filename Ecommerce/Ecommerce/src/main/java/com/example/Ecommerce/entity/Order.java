package com.example.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor()
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    Date orderDate;
    String shippingAddress;
    String oderStatus;
    String oderMail;
    Double totalAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> products;

}
