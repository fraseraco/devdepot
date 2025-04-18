package com.swe.backend.Controller;

import com.swe.backend.DTOs.SlimUserDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Login endpoint
    // Respond with UID for user

    @GetMapping("/all")
    public ResponseEntity<List<SlimUserDto>> getUsers() {
        return userService.getSlimUsers();
    }

    // authentication check @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable Long id) {
        return userService.getUserByID(id);
    }
}
