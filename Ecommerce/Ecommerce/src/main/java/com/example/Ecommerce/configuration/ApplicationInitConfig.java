package com.example.Ecommerce.configuration;

import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.enums.Role;
import com.example.Ecommerce.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationInitConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(CustomerRepository customerRepository){
      return args -> {
        if(customerRepository.findByEmail("admin@gmail.com") == null){
            HashSet<String> roles = new HashSet<>();
            roles.add(Role.ADMIN.name());
            Customer customer = Customer.builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin123456"))
                    .roles(roles)
                    .build();

            customerRepository.save(customer);
        }
      };
    };
}
