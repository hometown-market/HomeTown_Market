package com.example.market.domain.user.dto;

import java.util.Map;

public class NaverUserInfo extends CustomUserInfo {
    public NaverUserInfo(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("response"));
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getPhone() {
        return (String) attributes.get("mobile_e164");
    }

    @Override
    public String getProfileImgUrl() {
        return (String) attributes.get("profile_image");
    }

    @Override
    public String getRegistrationId() {
        return "naver";
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getNameAttributeKey() {
        return "email";
    }

    @Override
    public String getName() {
        return null;
    }
}
