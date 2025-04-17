package com.swe.backend.Mappers;

import com.swe.backend.DTOs.RoleDto;
import com.swe.backend.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.time.Instant;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    Role toRole(RoleDto roleDto);
    RoleDto toRoleDto(Role role);
}