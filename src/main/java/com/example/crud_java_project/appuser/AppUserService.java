package com.example.crud_java_project.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.crud_java_project.registration.token.ConfirmationToken;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    // private final ConfirmationTokenService confirmationToken/Service;
    private final static String USER_NOT_FOUND = "Unable to find %s by email!";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) 
        throws UsernameNotFoundException {

        System.out.println("\n 🚩 Unabled to find user");
        
        return appUserRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(
                String.format(USER_NOT_FOUND, email)
            ));
    }
    public String signUpUser(AppUser new_user) throws Exception { 
        // Check if the user exists
        boolean userExists = appUserRepository
            .findByEmail(new_user.getEmail())
            .isPresent();
        // Email already taken 
        if (userExists) {
            
            throw new IllegalStateException("Email Already Taken");
        }
            
        System.out.printf("\n 🤔 User already exists? %s", userExists );
        // New User, so we need to encode their password
        String userPassword = bCryptPasswordEncoder
            .encode(new_user.getPassword());

        // Set the user password
        System.out.printf("\n 👷👷 Setting the user password");
        new_user.setPassword(userPassword);
        //  Write to the database
        appUserRepository.save(new_user);


        String token = UUID.randomUUID().toString();
        // Send confirmation token 
        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(), 
            LocalDateTime.now().plusMinutes(15),
            new_user
        );

        // confirmationTokenService.saveConfirmationToken(confirmationToken);

        // send email 


        return token;
    } 
    public int enableAppUser(String email) { 
        return appUserRepository.enableAppUser(email);
    }
    
}
