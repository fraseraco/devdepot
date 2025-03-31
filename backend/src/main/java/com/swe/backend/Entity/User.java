package com.swe.backend.Entity;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonView;
import com.swe.backend.Views.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = User.TABLE_NAME, schema = "depotTEST", uniqueConstraints = {
        @UniqueConstraint(name = "username", columnNames = {"username"}),
        @UniqueConstraint(name = "email", columnNames = {"email"})
})
public class User {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID_NAME = "user_id";
    public static final String COLUMN_USERNAME_NAME = "username";
    public static final String COLUMN_PASSWORDHASH_NAME = "password_hash";
    public static final String COLUMN_EMAIL_NAME = "email";
    public static final String COLUMN_FIRSTNAME_NAME = "first_name";
    public static final String COLUMN_LASTNAME_NAME = "last_name";
    public static final String COLUMN_CREATEDAT_NAME = "created_at";
    public static final String COLUMN_LASTLOGIN_NAME = "last_login";


    private Long id;

    private String username;

    private String passwordHash;

    private String email;

    private String firstName;

    private String lastName;

    private Role role;

    private Instant createdAt;

    private Instant lastLogin;

    @JsonView(Views.Complete.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(Views.Public.class)
    @Column(name = COLUMN_USERNAME_NAME, nullable = false, length = 32)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(Views.Complete.class)
    @Column(name = COLUMN_PASSWORDHASH_NAME, nullable = false)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @JsonView(Views.Internal.class)
    @Column(name = COLUMN_EMAIL_NAME, nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonView(Views.Public.class)
    @Column(name = COLUMN_FIRSTNAME_NAME, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @JsonView(Views.Internal.class)
    @Column(name = COLUMN_LASTNAME_NAME, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @JsonView(Views.Internal.class)
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_CREATEDAT_NAME)
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }


    @JsonView(Views.Internal.class)
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_LASTLOGIN_NAME)
    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

}