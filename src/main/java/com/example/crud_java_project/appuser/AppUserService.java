package com.example.crud_java_project.appuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {


    private final AppUserRepository appUserRepository;
    private final static String USER_NOT_FOUND = 
        "Unable to find %s by email!";

    @Override
    public UserDetails loadUserByUsername(String email) 
        throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(
                String.format(USER_NOT_FOUND, email)
            ));
    }
    
}
