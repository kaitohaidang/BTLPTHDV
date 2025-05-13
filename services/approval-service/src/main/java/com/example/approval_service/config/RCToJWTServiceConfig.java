package com.example.approval_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RCToJWTServiceConfig {

    @Bean(name = "jwtRestClient")
    public RestClient jwtRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://jwt-service:8080/jwt")
                .build();
    }
}

