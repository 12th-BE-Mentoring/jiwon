package com.example.boardcrud.global.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey key;

    private static final String ACCESS_TOKEN = "access";
    private static final String REFRESH_TOKEN = "refresh";

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));    }

    public String generateAccessToken(String Id) {
        long accessTokenValidity = 1000L * 60 * 60; //1h
        return generateToken(Id, ACCESS_TOKEN, accessTokenValidity);
    }

    public String generateRefreshToken(String Id) {
        long refreshTokenValidity = 1000L * 60 * 60 * 24 * 14; //2주
        return generateToken(Id, REFRESH_TOKEN, refreshTokenValidity);
    }

    public String generateToken(String Id, String type, long validityTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + validityTime);

        return Jwts.builder()
                .subject(Id)
                .claim("type", type)
                .expiration(expiration)
                .issuedAt(new Date())
                .signWith(key)
                .compact();
    }
}
