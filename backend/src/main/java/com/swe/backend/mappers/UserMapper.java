package com.swe.backend.mappers;

import com.swe.backend.dtos.SlimUserDto;
import com.swe.backend.dtos.UserDto;
import com.swe.backend.dtos.UserRegistrationDto;
import com.swe.backend.entity.Role;
import com.swe.backend.entity.User;
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
    @Mapping(source = "isActive", target = "isActive")
    UserDto toUserDto(User user);

    @Mapping(source = "role.roleName", target = "roleName")
    SlimUserDto toSlimUserDto(User user);

    //    @Mapping(source = "role", target = "role.roleName")
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "password", target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "isActive", ignore = true)
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
    @Mapping(source = "isActive", target = "isActive")
    User toUser(UserDto dto);
}
