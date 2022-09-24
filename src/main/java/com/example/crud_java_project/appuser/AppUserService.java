package com.example.crud_java_project.appuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final static String USER_NOT_FOUND = "Unable to find %s by email!";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) 
        throws UsernameNotFoundException {

        System.out.println("\n  🚩 Unabled to find user");
        
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
        if (userExists) throw new IllegalStateException("Email Already Taken");
            
        System.out.printf("🤔 User already exists!  %s", userExists );
        
        // New User, so we need to encode their password
        String userPassword = bCryptPasswordEncoder
            .encode(new_user.getPassword());

        // Set the user password
        System.out.printf("👷👷 Setting the user password");
        new_user.setPassword(userPassword);

        // Send confirmation token 

        return "LOLOLLOLOLOLOLOLOLOLOLOLOLOL";
    } 
    
}
