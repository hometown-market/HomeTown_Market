package com.example.market.domain.user.dto;

import java.util.Map;

public class GoogleUserInfo extends CustomUserInfo {


    public GoogleUserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getEmail() {
        return (String) this.attributes.get("email");
    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getProfileImgUrl() {
        return null;
    }

    @Override
    public String getNameAttributeKey() {
        return getEmail();
    }

    @Override
    public String getRegistrationId() {
        return (String) this.attributes.get("registrationId");
    }



}
