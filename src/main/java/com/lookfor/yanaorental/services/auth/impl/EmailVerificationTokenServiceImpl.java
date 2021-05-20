package com.lookfor.yanaorental.services.auth.impl;

import com.lookfor.yanaorental.annotations.TransactionReadOnly;
import com.lookfor.yanaorental.annotations.TransactionRequired;
import com.lookfor.yanaorental.exceptions.auth.InvalidTokenException;
import com.lookfor.yanaorental.models.auth.EmailVerificationToken;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.repositories.auth.EmailVerificationTokenRepository;
import com.lookfor.yanaorental.services.auth.EmailVerificationTokenService;
import com.lookfor.yanaorental.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenServiceImpl implements EmailVerificationTokenService {
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Override
    @TransactionRequired
    public EmailVerificationToken createToken(User user) {
        String tokenString = TokenUtils.generateUUID();
        EmailVerificationToken token = new EmailVerificationToken(tokenString, user);
        return emailVerificationTokenRepository.save(token);
    }

    @Override
    @TransactionRequired
    public EmailVerificationToken regenerateToken(EmailVerificationToken existingToken) {
        String tokenString = TokenUtils.generateUUID();
        EmailVerificationToken newToken = new EmailVerificationToken(tokenString, existingToken.getUser());
        emailVerificationTokenRepository.delete(existingToken);
        return emailVerificationTokenRepository.save(newToken);
    }

    @Override
    @TransactionReadOnly
    public EmailVerificationToken findByToken(String token) {
        return emailVerificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Email verification token not found"));
    }

    @Override
    @TransactionRequired
    public void deleteByToken(EmailVerificationToken token) {
        emailVerificationTokenRepository.delete(token);
    }
}
