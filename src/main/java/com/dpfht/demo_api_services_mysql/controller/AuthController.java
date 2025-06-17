package com.dpfht.demo_api_services_mysql.controller;

import com.dpfht.demo_api_services_mysql.service.AuthService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody AuthRequest request) {
        return authService.register(request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest request) {
        return authService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/refresh")
    public Map<String, String> refreshToken(@RequestBody Map<String, String> req) {
        String refreshToken = req.get("refreshToken");
        String newAccessToken = authService.refresh(refreshToken);

        Map<String, String> result = new HashMap<>();
        result.put("accessToken", newAccessToken);

        return result;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        String refreshToken = "";
        String accessToken = "";

        if (request.getRefreshToken() != null) {
            refreshToken = request.getRefreshToken();
        }

        // Revoke Access Token
        if (request.getAccessToken() != null) {
            accessToken = request.getAccessToken();
        }

        if (!refreshToken.isEmpty() && !accessToken.isEmpty()) {
            authService.logout(refreshToken, accessToken);
        }

        return ResponseEntity.ok("Access and refresh tokens revoked successfully.");
    }
}

@Data
class AuthRequest {
    private String username;
    private String password;
}

@Data
class LogoutRequest {
    private String refreshToken;
    private String accessToken;
}