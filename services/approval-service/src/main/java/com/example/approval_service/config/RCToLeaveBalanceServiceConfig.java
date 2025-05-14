package com.example.approval_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RCToLeaveBalanceServiceConfig {

    @Bean(name = "leaveBalanceRestClient")
    public RestClient leaveBalanceRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://leave-service:8080/leave")
                .build();
    }
}