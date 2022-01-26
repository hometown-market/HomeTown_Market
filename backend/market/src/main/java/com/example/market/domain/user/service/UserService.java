package com.example.market.domain.user.service;

import com.example.market.domain.user.User;
import com.example.market.domain.user.dto.UserJoinRequest;
import com.example.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public User join(UserJoinRequest userJoinRequest) {
        String password = userJoinRequest.getPassword();
        String encode = passwordEncoder.encode(password);
        userJoinRequest.setPassword(encode);


        return repository.save(User.builder()
                .email(userJoinRequest.getEmail())
                .name(userJoinRequest.getName())
                .password(userJoinRequest.getPassword())
                .address(userJoinRequest.getAddress())
                .phone(userJoinRequest.getPhone())
                .role("ROLE_USER")
                .build()
        );


    }

    public boolean checkEmail(String email) {
        return repository.findByEmail(email) == null;
    }
}
