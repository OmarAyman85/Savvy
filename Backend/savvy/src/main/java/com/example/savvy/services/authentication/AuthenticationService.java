package com.example.savvy.services.authentication;

import com.example.savvy.model.entity.AuthenticationResponse;
import com.example.savvy.model.entity.User;

public interface AuthenticationService {
    AuthenticationResponse register(User request);

    AuthenticationResponse login(User request);


}
