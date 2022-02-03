package com.example.market.domain.user.controller;

import com.example.market.domain.user.User;
import com.example.market.domain.user.dto.UserEmailRequest;
import com.example.market.domain.user.dto.UserJoinRequest;
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

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "회원 가입", notes = "회원 가입")
    @PostMapping("/join")
    public String join(@Valid @RequestBody UserJoinRequest userJoinRequest, HttpServletResponse response) throws IOException {

        User join = userService.join(userJoinRequest);

        SecurityContextHolder.createEmptyContext().setAuthentication(new UsernamePasswordAuthenticationToken(join.getEmail(), join.getPassword()));
        String token = jwtTokenUtil.generateToken(join);
        token = "Bearer " + token;
        response.addHeader("Authorization", token);
        return null;
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

        boolean status;
        if (userService.checkEmail(userEmailRequest.getEmail())) {

            status = true;
            return status;
        } else {
            status = false;
            return status;
        }
    }

}
