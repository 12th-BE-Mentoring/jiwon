package com.example.boardcrud.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey key;

    private static final String ACCESS_TOKEN = "access";
    private static final String REFRESH_TOKEN = "refresh";

    //JWT를 만들기 전 필요한 SecretKey를 준비해 두는 것
    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));}
//jwt 만들 때 마다 키 설정하기 번거로우니까 저렇게 생성자 만듦

    public String generateAccessToken(String Id) {
        long accessTokenValidity = 1000L * 60 * 60; //1h
        return generateToken(Id, ACCESS_TOKEN, accessTokenValidity);
    }

    public String generateRefreshToken(String Id) {
        long refreshTokenValidity = 1000L * 60 * 60 * 24 * 14; //2주
        return generateToken(Id, REFRESH_TOKEN, refreshTokenValidity);
    }

    public String generateToken(String Id, String type, long validityTime) {
        Date now = new Date(); //토큰 발급 시기를 알 수 있어야 함
        Date expiration = new Date(now.getTime() + validityTime);

        return Jwts.builder()
                .subject(Id) //이름 (페이로드)
                .claim("type", type) //type access or refresh
                .expiration(expiration) //만료시간
                .issuedAt(new Date()) //토큰발급시간
                .signWith(key) //시그니쳐 만들기 (여기서SecretKey사용)
                .compact();
    }

    // 값 가져오기
    // Authorization Bearer lakdsjl... 여기서 뒤에 JWT만 뽑아냄
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "");
        }
        return null;
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }


    public Authentication getAuthentication(String token) {

        Claims claims = getClaims(token);

        String username = claims.getSubject();

        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                null
        );
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
