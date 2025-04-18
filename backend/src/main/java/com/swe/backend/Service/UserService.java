package com.swe.backend.Service;

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

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDto registerNewUser(UserRegistrationDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already taken.");
        }
        User user = UserMapper.toUser(dto);
        if (user == null)  { throw new RuntimeException(dto.getUsername() + " could not be added not exist."); }
            return UserMapper.toUserDto(userRepository.save(user));
    }

    public ResponseEntity<User> getUserByID(Long id) {
        User user =  userRepository.findById(id)
                .orElse(null);
        if (user == null) { throw new UserIdNotFoundException(id); }
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
