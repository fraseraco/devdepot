package com.swe.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.Entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}