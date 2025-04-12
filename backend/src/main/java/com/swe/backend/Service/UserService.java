package com.swe.backend.Service;

import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Entity.Role;
import com.swe.backend.Repository.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swe.backend.Entity.User;
import com.swe.backend.Exceptions.UserIdNotFoundException;
import com.swe.backend.Repository.UserRepository;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerNewUser(UserRegistrationDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already taken.");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        // ⚠️ ⚠️ PLAINTEXT PASSWORD ⚠️ ⚠️
        user.setPasswordHash(dto.getPassword()); // NEED TO CHANGE USING PASSWORD ENCODER
        // ⚠️ ⚠️ PLAINTEXT PASSWORD ⚠️ ⚠️
        user.setEmail(dto.getEmail());
        Role role = roleRepository.findByRoleName("USER_BASIC"); // Need to implement better business logic ***
        System.out.println("Role fetched: " + role);
        user.setRole(role);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCreatedAt(Instant.now());

        return userRepository.save(user);
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
