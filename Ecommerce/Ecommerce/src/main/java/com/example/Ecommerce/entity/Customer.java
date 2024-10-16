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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String email;
    String phoneNumber;
    String address;
    String password;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    List<Order> orders;

    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
    Cart cart;
}
