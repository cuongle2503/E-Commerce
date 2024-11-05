package com.example.Ecommerce.dto.request;

import com.example.Ecommerce.validator.DOBConstraint;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    String name;
    String email;
    String phoneNumber;
    String address;
    @Size(min = 10, message = "Password must be at least 10 characters.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Password must contain at least one letter and one number.")
    String password;
    Set<String> roles;
//    @DOBConstraint(min = 18, message = "INVALID_DOB")
}

