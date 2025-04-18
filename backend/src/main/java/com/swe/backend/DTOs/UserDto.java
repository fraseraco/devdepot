package com.swe.backend.DTOs;

import com.swe.backend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    Long id;
    String username;
    String passwordHash;
    String email;
    String firstName;
    String lastName;
    String roleName;
    Instant createdAt;
    Instant lastLogin;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.passwordHash = user.getPasswordHash();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roleName = user.getRole().getRoleName();
        this.createdAt = user.getCreatedAt();
        this.lastLogin = user.getLastLogin();
    }
}