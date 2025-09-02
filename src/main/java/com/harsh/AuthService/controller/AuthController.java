package com.harsh.AuthService.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.AuthService.dto.RegisterRequest;
import com.harsh.AuthService.dto.UserResponse;
import com.harsh.AuthService.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private   final UserService userService;
    @PostMapping("/register")
    public  ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request)
    {
UserResponse created=userService.register(request.getUsername(), request.getEmail(),request.getPassword() ,request.getRole());


         return ResponseEntity
                .created(URI.create("/api/auth/users/" + created.getId()))
                .body(created);

    }
// Check Health of Auth Service
      @GetMapping("/health")
    public String health() { return "Auth Service is up 🚀"; }



}
