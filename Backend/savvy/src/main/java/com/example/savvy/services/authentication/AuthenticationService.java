package com.example.savvy.services.authentication;

import com.example.savvy.dto.AuthenticationResponse;
import com.example.savvy.dto.UserDTO;
import com.example.savvy.model.entity.User;

public interface AuthenticationService {
    AuthenticationResponse register(UserDTO request);

    AuthenticationResponse authenticate(UserDTO request);
}
