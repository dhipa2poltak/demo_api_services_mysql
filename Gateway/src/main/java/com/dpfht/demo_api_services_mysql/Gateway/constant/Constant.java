package com.dpfht.demo_api_services_mysql.Gateway.constant;

import java.util.List;

public interface Constant {

    List<String> AUTH_WHITELIST = List.of(
            "/swagger-ui/**",
            "/api-docs/**",
            "/swagger-ui.html",
            "/api/auth/register",
            "/api/auth/login",
            "/api/auth/refresh",
            "/eureka"
    );

    class Message {
        public static final String FORBIDDEN_MESSAGE = "You don't have access";
        public static final String INVALID_TOKEN_MESSAGE = "Invalid access token";
    }
}
