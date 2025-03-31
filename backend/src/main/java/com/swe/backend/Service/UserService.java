package com.swe.backend.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swe.backend.Entity.User;
import com.swe.backend.Exceptions.UserIdNotFoundException;
import com.swe.backend.Repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
