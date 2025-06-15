package com.dpfht.demo_api_services_mysql.controller;

import com.dpfht.demo_api_services_mysql.service.AuthService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        return authService.register(request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        return authService.login(request.getUsername(), request.getPassword());
    }
}

@Data
class AuthRequest {
    private String username;
    private String password;
}
