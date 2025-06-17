package com.dpfht.demo_api_services_mysql.security;

import com.dpfht.demo_api_services_mysql.model.User;
import com.dpfht.demo_api_services_mysql.repository.AccessTokenRepository;
import com.dpfht.demo_api_services_mysql.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    private final AccessTokenRepository accessTokenRepository;

    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepository, AccessTokenRepository accessTokenRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (isValidAccessToken(token)) {
                String username = jwtUtil.extractUsername(token);
                User user = userRepository.findByUsername(username).orElse(null);
                if (user != null) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(username, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    public boolean isValidAccessToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(JwtUtil.key).build().parseClaimsJws(token);

            boolean retval = accessTokenRepository.findByToken(token)
                    .map(t -> !t.isRevoked())
                    .orElse(false);

            return retval;
        } catch (JwtException e) {
            return false;
        }
    }
}
