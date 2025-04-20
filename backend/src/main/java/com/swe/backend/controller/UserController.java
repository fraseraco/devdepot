package com.swe.backend.controller;

import com.swe.backend.dtos.SlimUserDto;
import com.swe.backend.dtos.UserDto;
import com.swe.backend.dtos.UserRegistrationDto;
import com.swe.backend.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Authentication> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        UserDto userDto = userService.registerNewUser(userRegistrationDto, passwordEncoder);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.status(HttpStatus.CREATED).body(authentication);
    }



    // returns current authenticated user
    @GetMapping("/me")
    public ResponseEntity<UserDto> currentUser(Authentication authentication) {
        String username = authentication.getName();
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<SlimUserDto>> getUsers() {
        return ResponseEntity.ok(userService.getSlimUsers());
    }

    // authentication check @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
