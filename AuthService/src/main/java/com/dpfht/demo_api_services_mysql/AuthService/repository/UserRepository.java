package com.dpfht.demo_api_services_mysql.AuthService.repository;

import com.dpfht.demo_api_services_mysql.AuthService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
