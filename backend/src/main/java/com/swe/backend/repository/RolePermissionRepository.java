package com.swe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.entity.RolePermission;
import com.swe.backend.entity.RolePermissionId;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}