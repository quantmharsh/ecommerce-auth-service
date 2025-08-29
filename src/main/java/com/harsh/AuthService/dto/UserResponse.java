package com.harsh.AuthService.dto;

import com.harsh.AuthService.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class UserResponse {

    private String username;
    private Long Id;
    private String email;
    private Role role;
  

}
