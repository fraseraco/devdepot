package com.swe.backend.Service;

import com.swe.backend.DTOs.SlimUserDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Mappers.UserMapper;
import com.swe.backend.Repository.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swe.backend.Entity.User;
import com.swe.backend.Exceptions.UserIdNotFoundException;
import com.swe.backend.Repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
    }

    public UserDto registerNewUser(UserRegistrationDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already taken.");
        }
        User user = UserMapper.toUser(dto);
        if (user == null)  { throw new RuntimeException(dto.getUsername() + " could not be added not exist."); }
            return UserMapper.toUserDto(userRepository.save(user));
    }

    public ResponseEntity<UserDto> getUserByID(Long id) {
        User user =  userRepository.findById(id)
                .orElse(null);
        if (user == null) { throw new UserIdNotFoundException(id); }
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users.stream().map(UserMapper::toUserDto).collect(Collectors.toList()));
    }

    public ResponseEntity<List<SlimUserDto>> getSlimUsers() {
        List<SlimUserDto> users = userRepository.findAll().stream().map(UserMapper::toSlimUserDto).toList();
        return ResponseEntity.ok(users);
    }
}
