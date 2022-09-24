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
        boolean isValid = emailValidator.test(request.getEmail());
        if (!isValid) throw new IllegalStateException("Email Not Valid");

        return appUserService.signUpUser(
            new AppUser(
                request.getFirstName(),
                request.getEmail(),
                request.getLastName(), 
                request.getPassword(),
                AppUserRole.USER
            )
        );
    }

}
