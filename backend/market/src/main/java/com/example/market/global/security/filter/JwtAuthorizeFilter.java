package com.example.market.global.security.filter;

import com.auth0.jwt.JWT;
import com.example.market.global.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizeFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private String username;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("JwtAuthorizeFilter.doFilterInternal JWT 요청 검증");
        String requestHeader = request.getHeader("Authroization");


        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String jwtToken = requestHeader.substring(7);

            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        }
    }
}
