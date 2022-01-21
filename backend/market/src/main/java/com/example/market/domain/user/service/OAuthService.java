package com.example.market.domain.user.service;

import com.example.market.domain.user.User;
import com.example.market.domain.user.dto.CustomUserInfo;
import com.example.market.domain.user.dto.UserInfoProvider;
import com.example.market.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OAuthService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    private UserInfoProvider userInfoProvider = new UserInfoProvider();

    public OAuthService() {
        super();
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("{}", userRequest);
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        CustomUserInfo userInfo = userInfoProvider.provider(registrationId, oAuth2User.getAttributes());

        User user = userRepository.findByEmail(userInfo.getEmail());

        if (user == null) {

            registerNew(registrationId, userInfo);
        }


        return new DefaultOAuth2User(oAuth2User.getAuthorities(), userInfo.getAttributes(), userInfo.getNameAttributeKey());

    }

    private User registerNew(String registrationId, CustomUserInfo userInfo) {

        User build = User.builder()
                .email(userInfo.getEmail())
                .role(userInfo.getRole())
                .phone(userInfo.getPhone())
                .profileImgUrl(userInfo.getProfileImgUrl())
                .provider(registrationId)
                .build();

        return userRepository.save(build);
    }


}
