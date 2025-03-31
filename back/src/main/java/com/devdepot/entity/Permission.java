package com.devdepot.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Setter
@Entity(name = Permission.ENTITY_NAME)
@Table(name = Permission.TABLE_NAME)
public class Permission {
    public static final String ENTITY_NAME = "Permission";
    public static final String TABLE_NAME = "permission";
    public static final String COLUMN_ID_NAME = "permission_id";
    public static final String COLUMN_PERMISSIONNAME_NAME = "permission_name";
    public static final String COLUMN_DESCRIPTION_NAME = "description";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permissionName;

    private String description;

    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = COLUMN_PERMISSIONNAME_NAME, nullable = false, length = 50)
    public String getPermissionName() {
        return permissionName;
    }

    @Column(name = COLUMN_DESCRIPTION_NAME)
    public String getDescription() {
        return description;
    }

}