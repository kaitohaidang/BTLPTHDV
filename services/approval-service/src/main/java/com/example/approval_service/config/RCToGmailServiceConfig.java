package com.example.approval_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RCToGmailServiceConfig {

    @Bean(name = "gmailRestClient")
    public RestClient gmailRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://gmail-service:8080/gmail")
                .build();
    }
}