package com.swe.backend.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.swe.backend.Entity.User;
import com.swe.backend.Service.UserService;
import com.swe.backend.Views.Views;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;         
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        return userService.getUsers();
    }

    @JsonView(Views.Public.class)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id) {
        return userService.getUserByID(id);
    }
}
