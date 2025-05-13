package com.example.jwt_service.controller;

import com.example.jwt_service.dto.JWTData;
import com.example.jwt_service.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JWTController {

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public String getJWT(
            @RequestBody JWTData jwtData
    ) {
        return jwtService.generateToken(jwtData.getName(), jwtData.getId(), jwtData.getManager());
    }

    @PostMapping("/getEmployeeId")
    public Integer getEmployeeId(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        return jwtService.getEmployeeId(token);
    }

    @PostMapping("/getManagerId")
    public Integer getManagerId(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        return jwtService.getManagerId(token);
    }
}
