package com.example.savvy.services.authentication;

import com.example.savvy.dto.AuthenticationResponse;
import com.example.savvy.dto.UserDTO;
import com.example.savvy.dto.VerificationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(UserDTO request);

    AuthenticationResponse authenticate(UserDTO request);

    ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    AuthenticationResponse verifyCode(VerificationRequest verificationRequest);
}
