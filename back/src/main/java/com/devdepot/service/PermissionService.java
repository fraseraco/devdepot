package com.devdepot.service;

import com.devdepot.entity.Permission;
import com.devdepot.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}