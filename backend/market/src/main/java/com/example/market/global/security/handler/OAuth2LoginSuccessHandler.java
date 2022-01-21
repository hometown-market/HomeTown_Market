package com.example.market.global.security.handler;

import com.example.market.global.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final JwtTokenUtil jwtTokenUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Map<String, Object> attributes = ((DefaultOAuth2User) authentication.getPrincipal()).getAttributes();
        Map<String, Object> claim = new HashMap<>(attributes);
        String jwtToken = jwtTokenUtil.generateToken(claim);
        response.addHeader("Authorization", "Bearer " + jwtToken);


        String targetUrl = determineTargetUrl(request, response, authentication);

        System.out.println("targetUrl = " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
