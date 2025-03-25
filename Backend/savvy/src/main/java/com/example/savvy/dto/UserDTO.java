package com.example.savvy.dto;

import com.example.savvy.model.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
}
