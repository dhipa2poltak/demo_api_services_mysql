package com.dpfht.demo_api_services_mysql.AuthService.repository;

import com.dpfht.demo_api_services_mysql.AuthService.model.RefreshToken;
import com.dpfht.demo_api_services_mysql.AuthService.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    @Transactional
    void deleteByUser(User user);
}
