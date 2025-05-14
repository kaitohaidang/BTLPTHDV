package com.example.approval_service.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.example.approval_service.dto.GmailData;

@Service
public class RCToGmailServiceService {

    private final RestClient gmailRestClient;

    public RCToGmailServiceService(
            @Qualifier("gmailRestClient") RestClient gmailRestClient
    ) {
        this.gmailRestClient = gmailRestClient;
    }

    public void sendGmail(String to, String subject, String content){
        gmailRestClient.post()
                .uri("/send")
                .body(new GmailData(to, subject, content))
                .retrieve()
                .body(Void.class);
    }
}