package com.devdepot.entity;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Setter
@Entity(name = User.ENTITY_NAME)
@Table(name = User.TABLE_NAME)
public class User {
    public static final String ENTITY_NAME = "User";
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID_NAME = "user_id";
    public static final String COLUMN_USERNAME_NAME = "username";
    public static final String COLUMN_PASSWORDHASH_NAME = "password_hash";
    public static final String COLUMN_EMAIL_NAME = "email";
    public static final String COLUMN_FIRSTNAME_NAME = "first_name";
    public static final String COLUMN_LASTNAME_NAME = "last_name";
    public static final String COLUMN_CREATEDAT_NAME = "created_at";
    public static final String COLUMN_LASTLOGIN_NAME = "last_login";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String passwordHash;

    private String email;

    private String firstName;

    private String lastName;

    private Role role;

    private Instant createdAt;

    private Instant lastLogin;

    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = COLUMN_USERNAME_NAME, nullable = false, length = 32)
    public String getUsername() {
        return username;
    }

    @Column(name = COLUMN_PASSWORDHASH_NAME, nullable = false)
    public String getPasswordHash() {
        return passwordHash;
    }

    @Column(name = COLUMN_EMAIL_NAME, nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    @Column(name = COLUMN_FIRSTNAME_NAME, length = 50)
    public String getFirstName() {
        return firstName;
    }

    @Column(name = COLUMN_LASTNAME_NAME, length = 50)
    public String getLastName() {
        return lastName;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_CREATEDAT_NAME)
    public Instant getCreatedAt() {
        return createdAt;
    }

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_LASTLOGIN_NAME)
    public Instant getLastLogin() {
        return lastLogin;
    }

}