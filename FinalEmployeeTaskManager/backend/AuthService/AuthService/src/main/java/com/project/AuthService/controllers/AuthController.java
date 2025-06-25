package com.project.AuthService.controllers;

import com.project.AuthService.dto.ChangePasswordRequest;
import com.project.AuthService.dto.LoginRequest;
import com.project.AuthService.dto.LoginResponse;
import com.project.AuthService.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/*
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}*/


import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request, @RequestHeader("Authorization") String token) {
        try {
            authService.changePassword(request, token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error changing password: " + e.getMessage());
        }
    }

}
