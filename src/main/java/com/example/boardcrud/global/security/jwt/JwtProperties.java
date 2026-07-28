package com.example.boardcrud.global.security.jwt;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secretKey;

    public JwtProperties(String secretKey) {
        this.secretKey = secretKey;
    }
}