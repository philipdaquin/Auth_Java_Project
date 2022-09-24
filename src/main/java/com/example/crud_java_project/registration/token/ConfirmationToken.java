package com.example.crud_java_project.registration.token;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.example.crud_java_project.appuser.AppUser;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {
    
    @SequenceGenerator(
        name = "confirmation_token_sequence", 
        sequenceName = "confirmation_token_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue( 
        strategy = GenerationType.SEQUENCE,
        generator = "confirmation_token_sequence"
    )

    private Long id;
        @Column(nullable = false)   
    private String token;
        @Column(nullable = false)
    private LocalDateTime createdAt;
        @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;


    @ManyToOne
    @JoinColumn(
        nullable = false,
        name = "app_user_id"
    )
    private AppUser appUser;

    public ConfirmationToken(
            String token, 
            LocalDateTime createdAt, 
            LocalDateTime expiresAt,
            LocalDateTime confirmedAt,
            AppUser appUser
        ) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.confirmedAt = confirmedAt;
        this.appUser = appUser;
    }


    
}
