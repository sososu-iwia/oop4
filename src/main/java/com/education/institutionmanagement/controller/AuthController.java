package com.education.institutionmanagement.controller;

import com.education.institutionmanagement.dto.RegisterRequest;
import com.education.institutionmanagement.entity.User;
import com.education.institutionmanagement.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = authService.register(request);
        user.setPasswordHash(null);
        return ResponseEntity.ok(user);
    }
}
