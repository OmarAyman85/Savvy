package com.example.savvy.services.authentication;

import com.example.savvy.dto.AuthenticationResponse;
import com.example.savvy.dto.UserDTO;
import com.example.savvy.model.entity.Token;
import com.example.savvy.model.entity.User;
import com.example.savvy.model.enums.TokenType;
import com.example.savvy.repository.TokenRepository;
import com.example.savvy.repository.UserRepository;
import com.example.savvy.security.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDTO request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        user = userRepository.save(user);

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(token, user);

        return new AuthenticationResponse(token, refreshToken);
    }

    public AuthenticationResponse authenticate(UserDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(token, user);

        return new AuthenticationResponse(token, refreshToken);
    }

    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);
        String username = jwtService.getUsernameFromToken(token);

        if (username != null) {
            var user = userRepository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isValidRefreshToken(token, user)) {
                String accessToken = jwtService.generateToken(user);
//                String refreshToken = jwtService.generateRefreshToken(user);

                revokeAllUserTokens(user);
                saveUserToken(accessToken, user);
                return new ResponseEntity(new AuthenticationResponse(accessToken, token), HttpStatus.OK);
//                var authResponse = new AuthenticationResponse(accessToken, refreshToken);
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//                response.setStatus(HttpServletResponse.SC_OK);
            }
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()) return;

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
            tokenRepository.save(token);
        });
    }

    private void saveUserToken(String token, User user) {
        Token tokenEntity = new Token();

        tokenEntity.setToken(token);
        tokenEntity.setUser(user);
        tokenEntity.setTokenType(TokenType.BEARER);
        tokenEntity.setExpired(false);
        tokenEntity.setRevoked(false);

        tokenRepository.save(tokenEntity);
    }
}
