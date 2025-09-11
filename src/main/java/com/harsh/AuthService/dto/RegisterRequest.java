package com.harsh.AuthService.dto;



import com.harsh.AuthService.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

//  DTO=Data Transfer Object
//  It’s a plain Java class that carries data between processes (like frontend → backend, or one service → another).
/**
 * Input validation guards your service early.
 * If role is null, we’ll default to ROLE_USER in the service.
 */
// Only this register request type of data  will be sent to service layer
//Basically it provides Type Safety.
//its DTo
 @Data
public class RegisterRequest {

     @NotBlank(message = "Username is required")
      @Size(min = 3, max = 64, message = "Username must be 3-64 characters")
     private  String username;
     @Email(message = "Email Should be valid")
     @NotBlank(message = "Email is required")
      @Size(max = 254, message = "Email too long")
    private String email;
      @NotBlank(message = "Password is required")
    @Size(min = 6, max = 72, message = "Password must be 6-72 characters")
    private String password;
    private Role role;


}
