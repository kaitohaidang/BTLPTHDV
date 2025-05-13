package com.example.approval_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RCToEmployeeServiceConfig {

    @Bean(name = "employeeRestClient")
    public RestClient jwtRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://employee-service:8080/employee")
                .build();
    }
}
