package com.harsh.AuthService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityBeans {

    //creating   this method here . So that  we can use anywhere in  Service.
    //And in future if we want to  change encryption technique we will need to  change here  only 
    @Bean
    public PasswordEncoder   passwordEncoder(){
         return new BCryptPasswordEncoder();
    }
}
