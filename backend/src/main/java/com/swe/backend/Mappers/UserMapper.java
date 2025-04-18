package com.swe.backend.Mappers;

import com.swe.backend.DTOs.SlimUserDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.roleName", target = "roleName")
    static UserDto toUserDto(User user) {
        return null;
    }

    SlimUserDto toSlimUserDto(User user);

    static User toUser(UserRegistrationDto registrationDto) {
        return null;
    }

    @Mapping(target = "role", ignore = true)
    @InheritInverseConfiguration
    User toUser(UserDto dto);
}
