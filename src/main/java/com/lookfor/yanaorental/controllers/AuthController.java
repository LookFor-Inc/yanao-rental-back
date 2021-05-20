package com.lookfor.yanaorental.controllers;

import com.lookfor.json.schemas.generated.OkResponse;
import com.lookfor.json.schemas.generated.auth.*;
import com.lookfor.yanaorental.events.OnGenerateResetLinkEvent;
import com.lookfor.yanaorental.events.OnRegistrationCompleteEvent;
import com.lookfor.yanaorental.exceptions.auth.InvalidTokenException;
import com.lookfor.yanaorental.models.auth.EmailVerificationToken;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.security.jwt.JwtTokenProvider;
import com.lookfor.yanaorental.services.auth.EmailVerificationTokenService;
import com.lookfor.yanaorental.services.auth.PasswordResetTokenService;
import com.lookfor.yanaorental.services.user.UserService;
import com.lookfor.yanaorental.utils.CookieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth/api/v1")
public class AuthController {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailVerificationTokenService emailVerificationTokenService;
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private CookieHelper cookieHelper;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request, HttpServletResponse response) {
        Authentication authentication = userService.authentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        cookieHelper.addAuthCookie(response, token);
        return new LoginResponse(token);
    }

    @PostMapping("/logout")
    public OkResponse logout(HttpServletResponse response) {
        cookieHelper.deleteAuthCookie(response);
        return new OkResponse("Successfully logout");
    }

    @PostMapping("/registration")
    public OkResponse registration(@RequestBody @Valid RegistrationRequest request) {
        User user = userService.registration(request);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
        return new OkResponse("User registered successfully. Check your email for verification");
    }

    @GetMapping("/registration/confirm")
    public OkResponse confirmRegistration(@RequestParam String token) {
        EmailVerificationToken emailVerificationToken = emailVerificationTokenService.findByToken(token);
        userService.confirmEmailRegistration(emailVerificationToken);
        return new OkResponse("User verified successfully");
    }

    @PostMapping("/password/reset-link")
    public OkResponse resetLink(@RequestBody @Valid PasswordResetLinkRequest passwordResetLinkRequest) {
        User user = userService.fetchByEmail(passwordResetLinkRequest.getEmail());
        eventPublisher.publishEvent(new OnGenerateResetLinkEvent(user));
        return new OkResponse("Password reset link sent successfully");
    }

    @PostMapping("/password/check-reset-token")
    public OkResponse checkPasswordResetLink(@RequestParam String token) {
        if (!passwordResetTokenService.existsByToken(token)) {
            throw new InvalidTokenException("Password reset token not found");
        }

        return new OkResponse("Password reset token found");
    }

    @PostMapping("/password/reset")
    public OkResponse resetPassword(@RequestBody @Valid PasswordResetRequest passwordResetRequest) {
        userService.resetPassword(passwordResetRequest);
        return new OkResponse("Password changed successfully");
    }
}
