package com.swe.backend.DTOs;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.swe.backend.Entity.User}
 */
@Data
public class SlimUserDto implements Serializable {
    Long id;
    String username;
    String passwordHash;
    String email;
    String roleRoleName;
}