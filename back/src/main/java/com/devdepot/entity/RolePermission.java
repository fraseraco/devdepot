package com.devdepot.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = RolePermission.ENTITY_NAME)
@Table(name = RolePermission.TABLE_NAME)
public class RolePermission {
    public static final String ENTITY_NAME = "RolePermission";
    public static final String TABLE_NAME = "role_permission";

    private RolePermissionId id;

    private Role role;

    private Permission permission;

    @EmbeddedId
    public RolePermissionId getId() {
        return id;
    }

    public void setId(RolePermissionId id) {
        this.id = id;
    }

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @MapsId("permissionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "permission_id", nullable = false)
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

}