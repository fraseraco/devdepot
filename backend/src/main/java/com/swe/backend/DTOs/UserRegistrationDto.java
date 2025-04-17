package com.swe.backend.DTOs;

import com.swe.backend.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto implements Serializable {

    private String username;

    @Size(min = 6, max = 32)
    private String password;

    @Email
    private String email;

    private String firstName;

    private String lastName;

    private String role;

}
