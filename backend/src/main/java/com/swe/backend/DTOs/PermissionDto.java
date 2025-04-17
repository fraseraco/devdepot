package com.swe.backend.DTOs;

import com.swe.backend.Entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Permission}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto implements Serializable {
    Long id;
    String permissionName;
    String description;
}