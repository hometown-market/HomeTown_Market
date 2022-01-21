package com.example.market.domain.user.dto;

import java.util.Map;

public class UserInfoProvider {

    public CustomUserInfo provider(String registrationId, Map<String, Object> attributes) {

        if (registrationId.equals("google")) {
            return new GoogleUserInfo(attributes);
        } else if (registrationId.equals("naver")) {
            return new NaverUserInfo(attributes);
        } else {
            return null;
        }
    }
}
