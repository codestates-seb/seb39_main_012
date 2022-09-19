package com.team012.server.config.oauth.provider;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; // getAttributes() 를 받을 것

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String)attributes.get("sub");
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}
