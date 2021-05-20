package com.lookfor.yanaorental.security.oauth2.user;

import java.util.Map;

public class VkOAuth2UserInfo extends OAuth2UserInfo {
    private final String email;

    public VkOAuth2UserInfo(Map<String, Object> attributes, String email) {
        super(attributes);
        this.email = email;
    }

    @Override
    public String getId() {
        return Integer.toString(((Integer) attributes.get("id")));
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("first_name");
    }

    @Override
    public String getLastName() {
        return (String) attributes.get("last_name");
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAvatarUrl() {
        return (String) attributes.get("photo_max");
    }
}
