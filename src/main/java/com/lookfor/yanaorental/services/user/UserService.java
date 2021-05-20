package com.lookfor.yanaorental.services.user;

import com.lookfor.json.schemas.generated.auth.LoginRequest;
import com.lookfor.json.schemas.generated.auth.PasswordResetRequest;
import com.lookfor.json.schemas.generated.auth.RegistrationRequest;
import com.lookfor.json.schemas.generated.user.UserAvatarUpdateResponse;
import com.lookfor.json.schemas.generated.user.UserInfoUpdateRequest;
import com.lookfor.json.schemas.generated.user.UserInfoUpdateResponse;
import com.lookfor.json.schemas.generated.user.UserPasswordUpdateRequest;
import com.lookfor.yanaorental.models.auth.AuthProvider;
import com.lookfor.yanaorental.models.auth.EmailVerificationToken;
import com.lookfor.yanaorental.models.auth.UserPrincipal;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.security.oauth2.user.OAuth2UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.function.Function;

public interface UserService extends UserDetailsService {
    User fetchById(long id);

    <T> T fetchById(long id, Function<User, T> function);

    User fetchByEmail(String email);

    Authentication authentication(LoginRequest request);

    User registration(RegistrationRequest request);

    User registration(AuthProvider authProvider, OAuth2UserInfo oAuth2UserInfo);

    boolean existsByEmail(String email);

    void isEmailUnique(String email);

    void confirmEmailRegistration(EmailVerificationToken token);

    void resetPassword(PasswordResetRequest request);

    default UserDetails loadUserByUsername(String username) {
        return new UserPrincipal(fetchByEmail(username));
    }

    default UserDetails loadUserDetailsById(long id) {
        return new UserPrincipal(fetchById(id));
    }

    UserInfoUpdateResponse updateInfo(long userId, UserInfoUpdateRequest request);

    UserAvatarUpdateResponse updateAvatar(long userId, MultipartFile avatar) throws IOException;

    void updatePassword(long userId, UserPasswordUpdateRequest request);
}
