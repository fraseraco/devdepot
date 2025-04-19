package com.swe.backend.UserDto;

import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.swe.backend.Entity.User}
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
}