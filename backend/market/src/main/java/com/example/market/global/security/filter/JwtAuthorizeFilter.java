package com.example.market.global.security.filter;

import com.example.market.domain.user.UserJwtToken;
import com.example.market.domain.user.repository.TokenRepository;
import com.example.market.domain.user.service.UserDetailsServiceImpl;
import com.example.market.global.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Slf4j
public class JwtAuthorizeFilter extends BasicAuthenticationFilter {


    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final TokenRepository tokenRepository;

    public JwtAuthorizeFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl userDetailsService, TokenRepository tokenRepository) {
        super(authenticationManager);
        this.tokenRepository = tokenRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        log.info("JwtAuthorizeFilter.doFilterInternal JWT 요청 검증");
        String requestHeader = request.getHeader("authorization");

        String jwtToken = null;
        String username = null;
        UserDetails userDetails = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            jwtToken = requestHeader.substring(7);

        }

        if (jwtToken != null) {
            try {
                username = jwtTokenUtil.getEmailFromToken(jwtToken);
                userDetails = userDetailsService.loadUserByUsername(username);
                jwtTokenUtil.validateToken(jwtToken, userDetails);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                log.info("검증 성공");

            } catch (ExpiredJwtException e) {
                username = (String) e.getClaims().get("email");

                Optional<UserJwtToken> token = tokenRepository.findById(username);
                UserJwtToken refreshToken = token.get();

                if (refreshToken == null) {
                    throw new RuntimeException();
                }

                if (jwtTokenUtil.isTokenExpired(refreshToken.getRefreshToken())) {
                    throw new IllegalStateException();
                } else {
                    Claims claims = jwtTokenUtil.getAllClaimsFromToken(jwtToken);
                    String accessToken = jwtTokenUtil.AccessGenerateToken(claims);
                    response.addHeader("Authorization", "Bearer " + accessToken);

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            }
        }
        filterChain.doFilter(request, response);
    }
}
