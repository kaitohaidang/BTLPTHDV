package com.example.jwt_service.service;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.exp}")
    private long exp;

    public String generateToken(String name, Integer id, boolean isManager) {

        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

        return Jwts.builder()
                .claim("unm", name)
                .claim("uid", id)
                .claim("uim", isManager)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Claims validateToken(String token) {
        Claims claims = extractClaims(token);

        if (claims.getExpiration().before(new Date())){
            return null;
        };

        if (claims.get("unm", String.class) == null ||
                claims.get("uid", Integer.class) == null ||
                claims.get("uim", Boolean.class) == null) {
            return null;
        }

        return claims;
    }

    public Integer getManagerId(String token) {
        Claims claims = validateToken(token);

        if (claims == null) {
            return null;
        }

        if (claims.get("uim", Boolean.class).equals(false)) {
            return null;
        } else {
            return claims.get("uid", Integer.class);
        }
    }

    public Integer getEmployeeId(String token) {
        Claims claims = validateToken(token);

        if (claims == null) {
            return null;
        }

        if (claims.get("uim", Boolean.class).equals(true)) {
            return null;
        } else {
            return claims.get("uid", Integer.class);
        }
    }

}
