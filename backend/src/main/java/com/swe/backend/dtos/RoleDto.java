package com.swe.backend.dtos;

import com.swe.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Role}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {
    Long id;
    String roleName;
    String briefDesc;
    Instant createdAt;
    Instant lastUpdate;
}