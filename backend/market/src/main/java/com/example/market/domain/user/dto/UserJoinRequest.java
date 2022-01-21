package com.example.market.domain.user.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserJoinRequest {


    @NotNull
    private String email;
    private String password;
    private String name;
    private String address;
    private String role;
}
