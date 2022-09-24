package com.example.crud_java_project.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory
        .getLogger(EmailService.class);

 

    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String email) {
        // TODO Auto-generated method stub
        System.out.printf("Sending Email to %s", email);
        try { 
            MimeMessage mineMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mineMessage, "utf=8");

            
        } catch (MessagingException e) { 
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");

        }
        
    }

    
}
