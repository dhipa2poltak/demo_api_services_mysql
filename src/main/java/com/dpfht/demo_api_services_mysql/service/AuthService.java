package com.dpfht.demo_api_services_mysql.service;

import com.dpfht.demo_api_services_mysql.model.RefreshToken;
import com.dpfht.demo_api_services_mysql.model.User;
import com.dpfht.demo_api_services_mysql.repository.RefreshTokenRepository;
import com.dpfht.demo_api_services_mysql.repository.UserRepository;
import com.dpfht.demo_api_services_mysql.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    private RefreshTokenRepository refreshTokenRepo;

    public Map<String, String> register(String username, String password) {
        if (userRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        userRepo.save(user);

        return generateTokenPair(user);
    }

    public Map<String, String> login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return generateTokenPair(user);
    }

    private Map<String, String> generateTokenPair(User user) {
        String accessToken = jwtUtil.generateAccessToken(user.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        refreshTokenRepo.deleteByUser(user);
        refreshTokenRepo.save(
                RefreshToken.builder()
                        .token(refreshToken)
                        .user(user)
                        .expiryDate(new Date(System.currentTimeMillis() + JwtUtil.EXPIRATION_REFRESH_TOKEN))
                        .build()
        );

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    public String refresh(String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        /*RefreshToken tokenInDB =*/ refreshTokenRepo.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        String username = jwtUtil.extractUsername(refreshToken);

        return jwtUtil.generateAccessToken(username);
    }
}
