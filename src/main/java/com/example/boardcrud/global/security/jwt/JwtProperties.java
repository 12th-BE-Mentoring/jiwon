package com.example.boardcrud.global.security.jwt;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secretKey;

    public JwtProperties(String secretKey) {
        this.secretKey = secretKey;
    }
}