package com.swe.backend.Controller;

import com.swe.backend.DTOs.AuthRequestDto;
import com.swe.backend.DTOs.AuthResponseDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Security.JwtUtil;
import com.swe.backend.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto body) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));

        UserDetails principal = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generate(
                principal.getUsername(),
                principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList());
        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Authentication> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        UserDto userDto = userService.registerNewUser(userRegistrationDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.status(HttpStatus.CREATED).body(authentication);
    }
}
