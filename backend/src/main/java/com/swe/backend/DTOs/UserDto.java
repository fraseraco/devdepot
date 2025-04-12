package com.swe.backend.DTOs;

import com.swe.backend.Entity.User;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String username;
    String passwordHash;
    String email;
    String firstName;
    String lastName;
    Instant createdAt;
    Instant lastLogin;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.passwordHash = user.getPasswordHash();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.createdAt = user.getCreatedAt();
        this.lastLogin = user.getLastLogin();
    }
}