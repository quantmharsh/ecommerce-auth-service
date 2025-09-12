package com.harsh.AuthService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email is Required")
    private String Email;
    @NotBlank(message = "Password is Required")
    private String Password;
}
