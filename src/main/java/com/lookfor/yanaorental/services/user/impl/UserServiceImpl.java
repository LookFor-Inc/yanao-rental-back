package com.lookfor.yanaorental.services.user.impl;

import com.lookfor.json.schemas.generated.auth.LoginRequest;
import com.lookfor.json.schemas.generated.auth.PasswordResetRequest;
import com.lookfor.json.schemas.generated.auth.RegistrationRequest;
import com.lookfor.json.schemas.generated.user.UserAvatarUpdateResponse;
import com.lookfor.json.schemas.generated.user.UserInfoUpdateRequest;
import com.lookfor.json.schemas.generated.user.UserInfoUpdateResponse;
import com.lookfor.json.schemas.generated.user.UserPasswordUpdateRequest;
import com.lookfor.yanaorental.annotations.TransactionReadOnly;
import com.lookfor.yanaorental.annotations.TransactionRequired;
import com.lookfor.yanaorental.exceptions.auth.UserAlreadyExistsException;
import com.lookfor.yanaorental.exceptions.rest.AlreadyExistsException;
import com.lookfor.yanaorental.exceptions.rest.ConflictException;
import com.lookfor.yanaorental.exceptions.rest.NotFoundException;
import com.lookfor.yanaorental.models.auth.AuthProvider;
import com.lookfor.yanaorental.models.auth.EmailVerificationToken;
import com.lookfor.yanaorental.models.auth.PasswordResetToken;
import com.lookfor.yanaorental.models.auth.ProviderAuth;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.models.user.UserInfo;
import com.lookfor.yanaorental.models.user.UserType;
import com.lookfor.yanaorental.repositories.UserRepository;
import com.lookfor.yanaorental.security.oauth2.user.OAuth2UserInfo;
import com.lookfor.yanaorental.services.AvatarService;
import com.lookfor.yanaorental.services.auth.EmailVerificationTokenService;
import com.lookfor.yanaorental.services.auth.PasswordResetTokenService;
import com.lookfor.yanaorental.services.user.UserDtoConverter;
import com.lookfor.yanaorental.services.user.UserService;
import com.lookfor.yanaorental.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AvatarService avatarService;
    private final AuthenticationManager authenticationManager;
    private final PasswordResetTokenService passwordResetTokenService;
    private final EmailVerificationTokenService emailVerificationTokenService;

    private final UserDtoConverter dtoConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    @TransactionReadOnly
    public User fetchById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    @Override
    @TransactionReadOnly
    public <T> T fetchById(long id, Function<User, T> function) {
        return function.apply(fetchById(id));
    }

    @Override
    @TransactionReadOnly
    public User fetchByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }

    @Override
    @TransactionRequired
    public Authentication authentication(LoginRequest request) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }

    @Override
    @TransactionRequired
    public User registration(RegistrationRequest request) {
        if (existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with email: " + request.getEmail());
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setType(UserType.valueOf(request.getType().name()));

        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar(avatarService.getAvatarUrl(user.getEmail()));

        userInfo.setUser(user);
        user.setUserInfo(userInfo);
        return userRepository.save(user);
    }

    @Override
    @TransactionRequired
    public User registration(AuthProvider authProvider, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setType(UserType.RENTER);
        user.setEnabled(true);

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(oAuth2UserInfo.getFirstName());
        userInfo.setLastName(oAuth2UserInfo.getLastName());
        userInfo.setAvatar(UrlUtils.stringToUrl(oAuth2UserInfo.getAvatarUrl()));

        ProviderAuth providerAuth = new ProviderAuth();
        providerAuth.setId(oAuth2UserInfo.getId());
        providerAuth.setProvider(authProvider);

        userInfo.setUser(user);
        user.setUserInfo(userInfo);
        providerAuth.setUser(user);
        user.setProviderAuth(new ArrayList<>() {{
            add(providerAuth);
        }});
        return userRepository.save(user);
    }

    @Override
    @TransactionReadOnly
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void isEmailUnique(String email) {
        if (existsByEmail(email)) {
            throw new AlreadyExistsException("User with email " + email + " already exists");
        }
    }

    @Override
    @TransactionRequired
    public void confirmEmailRegistration(EmailVerificationToken token) {
        emailVerificationTokenService.verifyExpiration(token);
        User user = token.getUser();
        emailVerificationTokenService.deleteByToken(token);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    @TransactionRequired
    public void resetPassword(PasswordResetRequest request) {
        PasswordResetToken passwordResetToken = passwordResetTokenService.findByToken(request.getToken());
        passwordResetTokenService.verifyExpiration(passwordResetToken);
        User user = passwordResetToken.getUser();
        passwordResetTokenService.deleteByToken(passwordResetToken);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Override
    @TransactionRequired
    public UserInfoUpdateResponse updateInfo(long userId, UserInfoUpdateRequest request) {
        User user = fetchById(userId);
        user.getUserInfo().setFirstName(request.getFirstName());
        user.getUserInfo().setLastName(request.getLastName());
        user.getUserInfo().setMiddleName(request.getMiddleName());
        return dtoConverter.convertToUserInfoUpdate(user);
    }

    @Override
    @TransactionRequired
    public UserAvatarUpdateResponse updateAvatar(long userId, MultipartFile avatar) {
        User user = fetchById(userId);
        // TODO: сохранение в mongoDb
        return dtoConverter.convertToUserAvatarUpdate(user);
    }

    @Override
    @TransactionRequired
    public void updatePassword(long userId, UserPasswordUpdateRequest request) {
        User user = fetchById(userId);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new ConflictException("Invalid old password");
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new ConflictException("New and old password are the same");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }
}
