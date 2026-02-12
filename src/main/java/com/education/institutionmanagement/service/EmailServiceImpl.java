package com.education.institutionmanagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from:noreply@edumanager.local}")
    private String fromEmail;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendRegistrationSuccess(String toEmail, String fullName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Registration successful – EduManager");
        message.setText("Hello " + (fullName != null && !fullName.isEmpty() ? fullName : "there") + "!\n\n" +
                "You have successfully registered in EduManager.\n\n" +
                "Thank you for joining us.");
        try {
            mailSender.send(message);
        } catch (Exception e) {
            // Log but do not fail registration
            System.err.println("Failed to send registration email: " + e.getMessage());
        }
    }
}
