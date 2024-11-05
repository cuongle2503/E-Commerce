package com.example.Ecommerce.configuration;

import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.entity.Role;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationInitConfig {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(CustomerRepository customerRepository) {
        return args -> {
            Optional<Role> adminRoleOpt = roleRepository.findById("ADMIN");

            Role adminRole = adminRoleOpt.orElseGet(() -> {
                Role role = new Role();
                role.setName("ADMIN");
                role.setDescription("Admin roles");
                return roleRepository.save(role);
            });

            if (customerRepository.findByEmail("admin@gmail.com") == null) {
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);

                Customer customer = Customer.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("admin123456"))
                        .build();

                customer.setRoles(roles);
                customerRepository.save(customer);
            }
        };
    }
}