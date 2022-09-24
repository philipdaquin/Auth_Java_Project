package com.example.crud_java_project.registration;

import org.springframework.stereotype.Service;

import com.example.crud_java_project.appuser.AppUser;
import com.example.crud_java_project.appuser.AppUserRole;
import com.example.crud_java_project.appuser.AppUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) throws Exception {
        System.out.printf("\n  👋Received a new request ");        
        
        boolean isValid = emailValidator.test(request.getEmail());
        System.out.printf("\n 👍👍👍   %s",isValid);

        if (!isValid) { 
            System.err.println("⛔ ERROR REGISTERING USER ⛔");
            throw new IllegalStateException("Email Not Valid");
        }

        
        System.out.printf("👨‍🚒 Creating the User");        
        return appUserService.signUpUser(
            new AppUser(
                request.getFirstName(),
                request.getLastName(), 
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
            )
        );
    }

}
