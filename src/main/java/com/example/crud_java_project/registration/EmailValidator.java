package com.example.crud_java_project.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;


@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String t) {
        System.out.printf("\n ✅ TESTING OUT EMAIL VALIDATOR");
        return true;
    }
    
}
