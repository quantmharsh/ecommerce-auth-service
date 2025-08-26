package com.harsh.AuthService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.AuthService.model.User;


@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    //user defined  methods

    Optional< User>  findByUserName(String username);
    Optional<User> findByEmail(String email);

}
