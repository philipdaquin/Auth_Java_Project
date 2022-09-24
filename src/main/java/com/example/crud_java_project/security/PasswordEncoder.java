package com.example.crud_java_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { 
        System.out.println("🔑 GENERATING A NEW LOCK 🔑");
        return new BCryptPasswordEncoder();
    }
}
