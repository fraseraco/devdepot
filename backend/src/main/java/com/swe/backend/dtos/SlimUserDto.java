package com.swe.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.swe.backend.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlimUserDto implements Serializable {
    Long id;
    String username;
    String email;
    String roleName;
}