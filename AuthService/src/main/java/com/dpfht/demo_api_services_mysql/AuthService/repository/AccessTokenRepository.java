package com.dpfht.demo_api_services_mysql.AuthService.repository;

import com.dpfht.demo_api_services_mysql.AuthService.model.AccessToken;
import com.dpfht.demo_api_services_mysql.AuthService.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    Optional<AccessToken> findByToken(String token);
    List<AccessToken> findAllByUserAndRevokedFalse(User user);

    @Transactional
    void deleteByUser(User user);
}
