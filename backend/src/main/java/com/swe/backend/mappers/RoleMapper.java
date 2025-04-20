package com.swe.backend.mappers;

import com.swe.backend.dtos.RoleDto;
import com.swe.backend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    Role toRole(RoleDto roleDto);
    RoleDto toRoleDto(Role role);
}