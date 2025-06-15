package com.dpfht.demo_api_services_mysql.repository;

import com.dpfht.demo_api_services_mysql.model.RefreshToken;
import com.dpfht.demo_api_services_mysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}
