package com.example.Ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;
    String detail;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    @JsonIgnore
    List<Product> products;
}
