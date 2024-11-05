package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
}
