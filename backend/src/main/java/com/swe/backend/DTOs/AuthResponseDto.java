package com.swe.backend.DTOs;

public class AuthResponseDto {
    String token;
    public AuthResponseDto(String token) {
        this.token = token;
    }
}
