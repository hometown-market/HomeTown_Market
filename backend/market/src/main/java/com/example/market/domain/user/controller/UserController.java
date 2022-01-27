package com.example.market.domain.user.controller;

import com.example.market.domain.user.User;
import com.example.market.domain.user.dto.UserEmailRequest;
import com.example.market.domain.user.dto.UserJoinRequest;
import com.example.market.domain.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @ApiOperation(value = "회원 가입", notes = "회원 가입")
    @PostMapping("/join")
    public User loign(@Valid @RequestBody UserJoinRequest userJoinRequest) {
        User join = userService.join(userJoinRequest);
        return null;
    }

    @ApiOperation(value = "로그인 요청", notes = "로그인 요청")
    @PostMapping("/login/")
    public String login() {

        return "ok";
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
