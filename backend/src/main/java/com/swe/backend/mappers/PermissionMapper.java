package com.swe.backend.mappers;

import com.swe.backend.dtos.PermissionDto;
import com.swe.backend.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);
    PermissionDto toPermissionDto(Permission permission);
    Permission toPermission(PermissionDto permissionDto);
}