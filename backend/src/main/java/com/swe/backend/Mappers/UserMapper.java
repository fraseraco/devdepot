package com.swe.backend.Mappers;

import com.swe.backend.DTOs.SlimUserDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.Entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source="role.roleName", target="roleName")
    UserDto userToUserDto(User user);

    SlimUserDto userToSlimUserDto(User user);

    @InheritInverseConfiguration
    User fromDto(UserDto dto);
}
