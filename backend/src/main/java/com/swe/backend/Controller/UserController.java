package com.swe.backend.Controller;

import com.swe.backend.DTOs.SlimUserDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;         
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        UserDto userDto = userService.registerNewUser(userRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    // returns current authenticated user
    @GetMapping("/me")
    public ResponseEntity<UserDto> currentUser(Authentication authentication) {
        String username = authentication.getName();
        return userService.findUserByUsername(username)
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
