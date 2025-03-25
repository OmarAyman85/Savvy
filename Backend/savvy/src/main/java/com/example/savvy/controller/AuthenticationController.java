package com.example.savvy.controller;

import com.example.savvy.dto.AuthenticationResponse;
import com.example.savvy.dto.UserDTO;
import com.example.savvy.services.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/")
    public String home() {
        return "Welcome to Savvy";
    }

//    @GetMapping("/register")
//    public String register() {
//        return "Welcome to registeration page";
//    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserDTO request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

//    @GetMapping("/login")
//    public String login() {
//        return "Welcome to login page";
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody UserDTO request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
