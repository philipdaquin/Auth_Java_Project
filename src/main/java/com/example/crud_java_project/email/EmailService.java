package com.example.crud_java_project.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory
        .getLogger(EmailService.class);

 

    private final JavaMailSender mailSender;


    /*
     Send email confirmation to the user
    */
    @Override
    @Async
    public void send(String to, String email)  {
        System.out.printf("Sending Email to %s", email);
        try { 
            MimeMessage mineMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mineMessage, "utf=8");
            System.out.printf("\n📦 Sending the email now..");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirmation your registration for JAVA AUTH");
            helper.setFrom("auth@apple.com");
            
            mailSender.send(mineMessage);
        } catch (MessagingException e) { 
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");

        }
        
    }

    
}
