package com.swe.backend.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Entity.User;
import com.swe.backend.Service.UserService;
import com.swe.backend.Views.Views;

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
        User user = userService.registerNewUser(userRegistrationDto);
        UserDto userDto = new UserDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    // Login endpoint
    // Respond with UID for user

    @JsonView(Views.Internal.class)
    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        return userService.getUsers();
    }

    @JsonView(Views.Public.class)
    // authentication check @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id) {
        return userService.getUserByID(id);
    }
}
