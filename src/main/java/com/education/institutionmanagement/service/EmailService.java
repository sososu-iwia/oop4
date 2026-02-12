package com.education.institutionmanagement.service;

/**
 * Abstraction for sending emails (DIP: AuthService depends on this interface).
 */
public interface EmailService {

    void sendRegistrationSuccess(String toEmail, String fullName);
}
