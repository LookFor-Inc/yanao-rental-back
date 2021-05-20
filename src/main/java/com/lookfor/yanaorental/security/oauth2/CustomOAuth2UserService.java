package com.lookfor.yanaorental.security.oauth2;

import com.lookfor.yanaorental.annotations.TransactionRequired;
import com.lookfor.yanaorental.exceptions.auth.OAuth2AuthenticationProcessingException;
import com.lookfor.yanaorental.models.auth.AuthProvider;
import com.lookfor.yanaorental.models.auth.ProviderAuth;
import com.lookfor.yanaorental.models.auth.UserPrincipal;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.repositories.UserRepository;
import com.lookfor.yanaorental.repositories.auth.ProviderAuthRepository;
import com.lookfor.yanaorental.security.oauth2.user.OAuth2UserInfo;
import com.lookfor.yanaorental.security.oauth2.user.OAuth2UserInfoFactory;
import com.lookfor.yanaorental.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProviderAuthRepository providerAuthRepository;

    @Override
    @TransactionRequired
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (Exception exception) {
            throw new InternalAuthenticationServiceException(exception.getMessage(), exception.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                oAuth2UserRequest,
                oAuth2User.getAttributes()
        );

        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        AuthProvider authProvider = AuthProvider.get(oAuth2UserRequest.getClientRegistration().getRegistrationId());

        Optional<ProviderAuth> providerAuthOptional = providerAuthRepository.findByIdAndProvider(
                oAuth2UserInfo.getId(),
                authProvider
        );
        User user;

        if (providerAuthOptional.isPresent()) {
            user = providerAuthOptional.get().getUser();
        } else {
            Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());

            if (userOptional.isPresent()) {
                throw new OAuth2AuthenticationProcessingException(String.format("User already exists with email: %s", oAuth2UserInfo.getEmail()));
            }

            user = userService.registration(authProvider, oAuth2UserInfo);
        }

        return new UserPrincipal(user, oAuth2User.getAttributes());
    }
}
