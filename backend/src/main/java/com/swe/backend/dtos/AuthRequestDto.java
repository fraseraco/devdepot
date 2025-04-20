package com.swe.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto {
    @NotBlank
    String username;

    @NotBlank
    String password;
}
