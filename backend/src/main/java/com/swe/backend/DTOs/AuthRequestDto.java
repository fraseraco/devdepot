package com.swe.backend.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto {
    @NotBlank
    String username;

    @NotBlank
    String password;
}
