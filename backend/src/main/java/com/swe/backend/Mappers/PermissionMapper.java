package com.swe.backend.Mappers;

import com.swe.backend.DTOs.PermissionDto;
import com.swe.backend.Entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);
    PermissionDto toPermissionDto(Permission permission);
    Permission toPermission(PermissionDto permissionDto);
}