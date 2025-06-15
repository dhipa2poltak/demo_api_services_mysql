package com.dpfht.demo_api_services_mysql.service;

import com.dpfht.demo_api_services_mysql.model.User;
import com.dpfht.demo_api_services_mysql.repository.UserRepository;
import com.dpfht.demo_api_services_mysql.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(String username, String password) {
        if (userRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        userRepo.save(user);

        return jwtUtil.generateToken(user.getUsername());
    }

    public String login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
