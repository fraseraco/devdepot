package com.devdepot.repository;

import com.devdepot.entity.RolePermission;
import com.devdepot.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}