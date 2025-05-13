package com.example.approval_service.service;

import com.example.approval_service.dto.BalanceUpdateData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RCToLeaveBalanceServiceService {

    private final RestClient leaveBalanceRestClient;

    public RCToLeaveBalanceServiceService(
            @Qualifier("leaveBalanceRestClient") RestClient leaveBalanceRestClient
    ) {
        this.leaveBalanceRestClient = leaveBalanceRestClient;
    }

    public Integer getBalance(Integer employeeId) {
        return leaveBalanceRestClient.post()
                .uri("/employeeId/{employeeId}", employeeId)
                .retrieve()
                .body(Integer.class);
    }

    public Integer updateBalance(Integer employeeId, Integer newBalance) {
        return leaveBalanceRestClient.post()
                .uri("/balance")
                .body(new BalanceUpdateData(employeeId, newBalance))
                .retrieve()
                .body(Integer.class);
    }
}