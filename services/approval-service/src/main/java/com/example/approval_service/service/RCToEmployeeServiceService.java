package com.example.approval_service.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RCToEmployeeServiceService {

    private final RestClient employeeRestClient;

    public RCToEmployeeServiceService(
            @Qualifier("employeeRestClient") RestClient employeeRestClient
    ) {
        this.employeeRestClient = employeeRestClient;
    }

    public String getEmployeeEmailById(Integer employeeId) {
        return employeeRestClient.get()
                .uri("/getEmployeeEmail/{employeeId}", employeeId)
                .retrieve()
                .body(String.class);
    }

    public List<Integer> getEmployeeIdsByManagerId(Integer managerId) {
        return employeeRestClient.get()
                .uri("/getListEmployeeIds/{managerId}", managerId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Integer>>() {});
    }

}