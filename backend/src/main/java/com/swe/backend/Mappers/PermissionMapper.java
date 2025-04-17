package com.swe.backend.Mappers;

import com.swe.backend.DTOs.PermissionDto;
import com.swe.backend.Entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;


@Mapper
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);
    PermissionDto toPermissionDto(Permission permission);
    Permission toPermission(PermissionDto permissionDto);
}