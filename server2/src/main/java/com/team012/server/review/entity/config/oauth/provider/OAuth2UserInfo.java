package com.team012.server.review.entity.config.oauth.provider;

public interface OAuth2UserInfo {

    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();

}
