package com.swe.backend.Service;

import com.swe.backend.DTOs.SlimUserDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Entity.Role;
import com.swe.backend.Mappers.UserMapper;
import com.swe.backend.Repository.RoleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swe.backend.Entity.User;
import com.swe.backend.Exceptions.UserIdNotFoundException;
import com.swe.backend.Repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto registerNewUser(UserRegistrationDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already taken. " + dto.getUsername());
        }

        User user = userMapper.toUser(dto);
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        Role defaultRole = roleRepository.findByRoleName("USER_BASIC")
                .orElseThrow(() -> new IllegalStateException("Default role not found"));
        user.setRole(defaultRole);

        return userMapper.toUserDto(userRepository.save(user));
    }

    // GET user by username -> THROWS error when user DNE
    public Optional<UserDto> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username)
                .map(userMapper::toUserDto)
                .orElseThrow(() ->
                        new NoSuchElementException("User '%s' not found".formatted(username))));
    }

    // FIND user by name -> doesn't throw error when user DNE
    public Optional<UserDto> findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDto);
    }

    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDto);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public List<SlimUserDto> getSlimUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toSlimUserDto)
                .toList();
    }

    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<UserDto> updateUserRole(Long userId, String roleName) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Role> roleOpt = roleRepository.findByRoleName(roleName);

        if (userOpt.isPresent() && roleOpt.isPresent()) {
            User user = userOpt.get();
            user.setRole(roleOpt.get());
            return Optional.of(userMapper.toUserDto(userRepository.save(user)));
        }

        return Optional.empty();
    }
}
