package com.swe.backend.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(@NotBlank String username);
    Optional<User> findByUsername(@NotBlank String username);

    Optional<User> getUserById(Long id);
}