package com.devdepot.service;

import com.devdepot.entity.RolePermission;
import com.devdepot.entity.RolePermissionId;
import com.devdepot.repository.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;

    @Autowired
    public RolePermissionService(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    public List<RolePermission> getAllRolePermissions() {
        return rolePermissionRepository.findAll();
    }

    public Optional<RolePermission> getRolePermissionById(RolePermissionId id) {
        return rolePermissionRepository.findById(id);
    }

    public RolePermission saveRolePermission(RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }

    public void deleteRolePermission(RolePermissionId id) {
        rolePermissionRepository.deleteById(id);
    }
}