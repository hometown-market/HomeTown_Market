package com.example.market.domain.user.controller;

import com.example.market.domain.user.User;
import com.example.market.domain.user.dto.UserJoinRequest;
import com.example.market.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/join")
    public User loign(@RequestBody UserJoinRequest userJoinRequest) {
        User join = userService.join(userJoinRequest);
        return null;
    }

    @PostMapping("/login/")
    public String login() {
        return "ok";
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/api/v1/login")
    public String loginGet() {
        return "login page";
    }
}
