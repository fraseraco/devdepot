package com.swe.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.Entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String user);
}