package com.example.market.domain.user.controller;

import com.example.market.domain.user.User;
import com.example.market.domain.user.dto.UserEmailRequest;
import com.example.market.domain.user.dto.UserJoinRequest;
import com.example.market.domain.user.repository.TokenRepository;
import com.example.market.domain.user.service.UserService;
import com.example.market.global.security.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final TokenRepository tokenRepository;

    @ApiOperation(value = "회원 가입", notes = "회원 가입")
    @PostMapping("/join")
    public long join(@Valid @RequestBody UserJoinRequest userJoinRequest, HttpServletResponse response) throws IOException {

        User join = userService.join(userJoinRequest);

        SecurityContextHolder.createEmptyContext().setAuthentication(new UsernamePasswordAuthenticationToken(join.getEmail(), join.getPassword()));
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", join.getEmail());
        String accessToken = jwtTokenUtil.AccessGenerateToken(claims);
        String refreshToken = jwtTokenUtil.RefreshGenerateToken(claims);

        jwtTokenUtil.StoreRefreshToken((String) claims.get("email"), refreshToken);
        response.addHeader("Authorization", "Bearer " + accessToken);
        return join.getId();
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/login")
    public String loginGet() {
        return "login page";
    }

    @ApiOperation(value = "이메일 중복체크", notes = "이메일 중복체크")
    @PostMapping("/api/login/email-check")
    public boolean emailCheck(@Validated @RequestBody UserEmailRequest userEmailRequest, HttpServletRequest request, HttpServletResponse response) {

        if (userService.checkEmail(userEmailRequest.getEmail())) {

            return true;
        } else {
            return false;
        }
    }

}
