package com.devdepot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = Permission.ENTITY_NAME)
@Table(name = Permission.TABLE_NAME)
public class Permission {
    public static final String ENTITY_NAME = "Permission";
    public static final String TABLE_NAME = "permission";
    public static final String COLUMN_ID_NAME = "permission_id";
    public static final String COLUMN_PERMISSIONNAME_NAME = "permission_name";
    public static final String COLUMN_DESCRIPTION_NAME = "description";


    private Long id;

    private String permissionName;

    private String description;

    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = COLUMN_PERMISSIONNAME_NAME, nullable = false, length = 50)
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Column(name = COLUMN_DESCRIPTION_NAME)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}