package com.swe.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String user);
}