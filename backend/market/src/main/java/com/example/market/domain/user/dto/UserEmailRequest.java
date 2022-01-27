package com.example.market.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserEmailRequest {

    @NotBlank
    @Email
    private String email;

}
