package com.example.market.domain.user.dto;

import java.util.Map;

public abstract class CustomUserInfo {

    protected Map<String, Object> attributes;

    public CustomUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getEmail();

    public abstract String getPhone();

    public abstract String getAddress();

    public abstract String getName();

    public abstract String getRegistrationId();

    public String getRole() {
        return "ROLE_USER";
    }

    public abstract String getProfileImgUrl();

    public Map<String, Object> getAttributes() {
        return attributes;
    }
    public abstract String getNameAttributeKey();
}
