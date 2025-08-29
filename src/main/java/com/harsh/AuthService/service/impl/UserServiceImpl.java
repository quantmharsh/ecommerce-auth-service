package com.harsh.AuthService.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harsh.AuthService.dto.UserResponse;
import com.harsh.AuthService.model.Role;
import com.harsh.AuthService.model.User;
import com.harsh.AuthService.repository.UserRepository;
import com.harsh.AuthService.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service // Marks this as a Spring service component
@RequiredArgsConstructor // Generates constructor injection for final fields
@Transactional // Wraps all public methods in a transaction
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(String username, String email, String rawPassword, Role role) {
        try {
            if (username.isEmpty() || email.isEmpty() || rawPassword.isEmpty()) {
                throw new IllegalArgumentException("All fields required");
            }
            if (userRepository.existByEmail(email)) {
                throw new IllegalArgumentException("Email Already exists. Try to  login ");
            }
            if (userRepository.existsByUserName(username)) {
                throw new IllegalArgumentException("Username  Already exists");
            }

            String encodedPassword = passwordEncoder.encode(rawPassword);
            Role finalRole = (role == null) ? Role.ROLE_USER : role;
            // create user and store in db
            User user = User.builder().username(username).email(email).password(encodedPassword).role(finalRole)
                    .build();

            // save into db
            userRepository.save(user);
            return   new UserResponse(
                 user.getUsername(),
                    user.getId(),
                    user.getEmail(),
            user.getRole()
               
            );

        } catch (Exception e) {
            throw new Error("Something Went Wrong . Please Try Again Later" + e);
        }

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
