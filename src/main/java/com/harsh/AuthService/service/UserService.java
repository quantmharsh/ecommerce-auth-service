
package com.harsh.AuthService.service;

import java.util.Optional;

import com.harsh.AuthService.dto.UserResponse;
import com.harsh.AuthService.model.Role;
import com.harsh.AuthService.model.User;


public interface UserService {

  public UserResponse register(String username , String  email  , String password , Role role);
  public boolean login(String email, String rawPassword);

 public   Optional<User> findByEmail(String email);
 public   Optional<User> findByUsername(String username);
  



}
