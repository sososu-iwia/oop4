package com.education.institutionmanagement.service;

import com.education.institutionmanagement.dto.RegisterRequest;
import com.education.institutionmanagement.entity.User;
import com.education.institutionmanagement.exception.DuplicateEmailException;
import com.education.institutionmanagement.exception.ValidationException;
import com.education.institutionmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public AuthService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public User register(RegisterRequest request) {
        validateRegisterRequest(request);
        if (userRepository.findByEmail(request.getEmail().trim()).isPresent()) {
            throw new DuplicateEmailException("Email already registered: " + request.getEmail());
        }
        User user = new User();
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setPasswordHash(hashPassword(request.getPassword()));
        user.setFullName(request.getFullName() != null ? request.getFullName().trim() : "");
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);
        emailService.sendRegistrationSuccess(user.getEmail(), user.getFullName());
        return user;
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (request == null || request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (request.getPassword() == null || request.getPassword().length() < 4) {
            throw new ValidationException("Password must be at least 4 characters");
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing not available", e);
        }
    }
}
