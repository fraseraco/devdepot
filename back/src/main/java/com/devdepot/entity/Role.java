package com.devdepot.entity;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Setter
@Entity(name = Role.ENTITY_NAME)
@Table(name = Role.TABLE_NAME)
public class Role {
    public static final String ENTITY_NAME = "Role";
    public static final String TABLE_NAME = "role";
    public static final String COLUMN_ID_NAME = "role_id";
    public static final String COLUMN_ROLENAME_NAME = "role_name";
    public static final String COLUMN_BRIEFDESC_NAME = "brief_desc";
    public static final String COLUMN_CREATEDAT_NAME = "created_at";
    public static final String COLUMN_LASTUPDATE_NAME = "last_update";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    private String briefDesc;

    private Instant createdAt;

    private Instant lastUpdate;

    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = COLUMN_ROLENAME_NAME, nullable = false, length = 50)
    public String getRoleName() {
        return roleName;
    }

    @Column(name = COLUMN_BRIEFDESC_NAME, length = 50)
    public String getBriefDesc() {
        return briefDesc;
    }

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_CREATEDAT_NAME)
    public Instant getCreatedAt() {
        return createdAt;
    }

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_LASTUPDATE_NAME)
    public Instant getLastUpdate() {
        return lastUpdate;
    }

}