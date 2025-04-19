package com.swe.backend.Mappers;

import com.swe.backend.DTOs.SlimUserDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Entity.Role;
import com.swe.backend.Entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    private Role map(String roleName) {
        if (roleName == null) return null;
        Role role = new Role();
        role.setRoleName(roleName);
        return role;
    }
    @Mapping(source = "role.roleName", target = "roleName")
    UserDto toUserDto(User user);

    @Mapping(source = "role.roleName", target = "roleName")
    SlimUserDto toSlimUserDto(User user);

    //    @Mapping(source = "role", target = "role.roleName")
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "password", target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @InheritInverseConfiguration
    User toUser(UserRegistrationDto registrationDto);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    User toUser(UserDto dto);
}
