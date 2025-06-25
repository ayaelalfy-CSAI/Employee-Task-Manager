package com.project.AuthService.services;

import com.project.AuthService.dto.ChangePasswordRequest;
import com.project.AuthService.dto.LoginRequest;
import com.project.AuthService.dto.LoginResponse;
import com.project.AuthService.exceptions.InvalidCredentialsException;
import com.project.AuthService.exceptions.ResourceNotFoundException;
import com.project.AuthService.models.User;
import com.project.AuthService.repositories.UserRepository;
import com.project.AuthService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {

        System.out.println("Login request received: " + request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password.");
        }

        System.out.println("Login successful for user: " + user.getEmail());
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name(),user.getId());

        return new LoginResponse(
            user.getId(),
            user.getUsername(),
            user.getRole(),
            user.isPasswordChanged(),
            "Login successful!",
            token
        );
    }

    public void changePassword(ChangePasswordRequest request, String authHeader) {
        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setPasswordChanged(true);

        userRepository.save(user);
    }

}
