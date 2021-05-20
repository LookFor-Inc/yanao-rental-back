package com.lookfor.yanaorental.security.oauth2.user;

import com.lookfor.yanaorental.exceptions.auth.OAuth2AuthenticationProcessingException;
import com.lookfor.yanaorental.models.auth.AuthProvider;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import java.util.List;
import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(OAuth2UserRequest oAuth2UserRequest, Map<String, Object> attributes) {
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();

        if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.VK.toString())) {
            if (attributes.get("response") instanceof List) {
                String email = (String) oAuth2UserRequest.getAdditionalParameters().get("email");
                List<Map<String, Object>> list = (List<Map<String, Object>>) attributes.get("response");
                Map<String, Object> map = list.get(0);
                return new VkOAuth2UserInfo(map, email);
            }

            throw new OAuth2AuthenticationProcessingException("Error parsing VK response.");
        } else {
            throw new OAuth2AuthenticationProcessingException("Login with " + registrationId + " is not supported yet.");
        }
    }
}
