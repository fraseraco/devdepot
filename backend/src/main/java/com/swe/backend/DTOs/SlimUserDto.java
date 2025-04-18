package com.swe.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.swe.backend.Entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlimUserDto implements Serializable {
    Long id;
    String username;
    String email;
    String roleRoleName;
}