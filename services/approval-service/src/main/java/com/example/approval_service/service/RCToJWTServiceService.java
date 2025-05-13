package com.example.approval_service.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RCToJWTServiceService {

    private final RestClient jwtRestClient;

    public RCToJWTServiceService(
            @Qualifier("jwtRestClient") RestClient jwtRestClient
    ) {
        this.jwtRestClient = jwtRestClient;
    }

    public Integer getEmployeeId(String authorizationHeader) {
        return jwtRestClient.post()
                .uri("/getEmployeeId")
                .header("Authorization", authorizationHeader)
                .retrieve()
                .body(Integer.class);
    }

    public Integer getManagerId(String authorizationHeader) {
        return jwtRestClient.post()
                .uri("/getManagerId")
                .header("Authorization", authorizationHeader)
                .retrieve()
                .body(Integer.class);
    }

}
