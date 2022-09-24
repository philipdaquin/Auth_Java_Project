package com.example.crud_java_project.registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/registration/")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) throws Exception { 
        System.out.printf("\n Received a new request %s", request);        
        
        System.err.println("\n ⛔ ERROR REGISTERING USER ⛔");


        return registrationService.register(request);
    }
}
