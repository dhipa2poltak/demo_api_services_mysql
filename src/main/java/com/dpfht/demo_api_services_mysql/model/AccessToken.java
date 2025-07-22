package com.dpfht.demo_api_services_mysql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "access_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private boolean revoked;

    @ManyToOne
    private User user;
}
