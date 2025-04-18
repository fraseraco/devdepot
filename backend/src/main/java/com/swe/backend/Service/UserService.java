package com.swe.backend.Service;

import com.swe.backend.DTOs.SlimUserDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Entity.Role;
import com.swe.backend.Mappers.UserMapper;
import com.swe.backend.Repository.RoleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.swe.backend.Entity.User;
import com.swe.backend.Exceptions.UserIdNotFoundException;
import com.swe.backend.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDto registerNewUser(UserRegistrationDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already taken.");
        }
        User user = userMapper.toUser(dto);
        if (user == null)  { throw new RuntimeException(dto.getUsername() + " could not be added not exist."); }
            return userMapper.toUserDto(userRepository.save(user));
    }

    public Optional<UserDto> getUserById(Long id) {
        User user =  userRepository.findById(id)
                .orElse(null);
        if (user == null) { throw new UserIdNotFoundException(id); }
        return Optional.ofNullable(userMapper.toUserDto(user));
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserDto).toList();
//                ResponseEntity.ok(users.stream().map(userMapper::toUserDto).collect(Collectors.toList()));
    }

    public List<SlimUserDto> getSlimUsers() {
        return userRepository.findAll().stream().map(userMapper::toSlimUserDto).toList();
    }

    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<UserDto> updateUserRole(Long uid, String roleName) {
        Optional<User> userOpt = userRepository.findById(uid);
        Optional<Role> roleOpt = roleRepository.findByRoleName(roleName);

        if (userOpt.isPresent() && roleOpt.isPresent()) {
            User user = userOpt.get();
            user.setRole(roleOpt.get());
            return Optional.of(UserMapper.toDto(userRepository.save(user)));
        }

        return Optional.empty();
    }
}
