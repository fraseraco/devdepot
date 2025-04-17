package com.swe.backend.DTOs;

import com.swe.backend.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserRegistrationDto implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6, max = 32)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String role;


}
