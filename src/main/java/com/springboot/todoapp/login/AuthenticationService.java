package com.springboot.todoapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public Boolean authenticate(String name, String password) {
        Boolean isValidName = name.equalsIgnoreCase("name");
        Boolean isValidPassword = password.equalsIgnoreCase("admin");
        return isValidName && isValidPassword;
    }
}
