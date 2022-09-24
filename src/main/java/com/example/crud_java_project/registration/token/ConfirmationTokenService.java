package com.example.crud_java_project.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) { 
        System.out.print("👷 Saving Confirmation Token");
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }
    
    public int setConfirmedAt(String token) { 
        return confirmationTokenRepository.updateConfirmedAt(
            token, 
            LocalDateTime.now()
        );
    }
}
