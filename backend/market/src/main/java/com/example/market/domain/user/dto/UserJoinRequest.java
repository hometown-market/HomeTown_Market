package com.example.market.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor

public class UserJoinRequest {


    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String phone;
    private String name;
    private String address;
    private String role;
}
