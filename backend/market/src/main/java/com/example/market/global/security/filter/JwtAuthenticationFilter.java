package com.example.market.global.security.filter;


import com.example.market.domain.user.User;
import com.example.market.domain.user.dto.UserLoginRequest;
import com.example.market.domain.user.repository.TokenRepository;
import com.example.market.global.security.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final TokenRepository tokenRepository;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper om = new ObjectMapper();

        try {
            UserLoginRequest user = om.readValue(request.getInputStream(), UserLoginRequest.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            log.info("로그인 시도 {} {} ", user.getEmail(), user.getPassword());

            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        log.info("로그인 성공");

        User user = (User) authResult.getPrincipal();

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);


        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        long id = user.getId();
        String accessToken = jwtTokenUtil.AccessGenerateToken(claims);
        String refreshToken = jwtTokenUtil.RefreshGenerateToken(claims);

        jwtTokenUtil.StoreRefreshToken((String) claims.get("email"), refreshToken);
        response.addHeader("Authorization", "Bearer " + accessToken);
        String s = "{\"id\" : " + "\"" + String.valueOf(id) + "\"" + "}";
        response.getWriter().write(s);

    }
}
