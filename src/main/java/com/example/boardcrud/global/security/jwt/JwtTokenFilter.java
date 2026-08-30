package com.example.boardcrud.global.security.jwt;

import com.example.boardcrud.global.error.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = jwtProvider.resolveToken(request);

        try {
            if (token != null) {

                Authentication authentication =
                        jwtProvider.getAuthentication(token);

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            response.setStatus(e.getErrorCode().getStatus().value());
            response.setContentType("application/json;charset=UTF-8");

            String body = String.format(
                    "{\"code\":\"%s\",\"message\":\"%s\"}",
                    e.getErrorCode().name(),
                    e.getErrorCode().getMessage()
            );

            response.getWriter().write(body);
        }
    }
}