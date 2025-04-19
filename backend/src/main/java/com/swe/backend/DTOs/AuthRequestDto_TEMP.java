package com.swe.backend.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto_TEMP {
    @NotBlank
    String username;

    @NotBlank
    String password;
}
