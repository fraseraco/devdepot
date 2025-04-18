package com.swe.backend.Repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(@NotBlank String username);
}