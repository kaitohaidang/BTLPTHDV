package com.example.gmail_service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gmail")
public class GmailController {

    private final GmailService gmailService;

    @PostMapping("/send")
    public void sendGmail(@RequestBody GmailData gmailData) {
        System.out.println(gmailData);
        gmailService.sendEmail(
                gmailData.getTo(),
                gmailData.getSubject(),
                gmailData.getContent()
        );
    }
}
