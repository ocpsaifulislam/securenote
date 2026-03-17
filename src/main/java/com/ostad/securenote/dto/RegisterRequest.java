package com.ostad.securenote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Role is required")
    @Pattern(
            regexp = "^(USER|ADMIN)$",
            message = "Role must be either USER or ADMIN"
    )
    private String role;
}