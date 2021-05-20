package com.lookfor.yanaorental.services.auth.impl;

import com.lookfor.yanaorental.annotations.TransactionReadOnly;
import com.lookfor.yanaorental.annotations.TransactionRequired;
import com.lookfor.yanaorental.exceptions.auth.InvalidTokenException;
import com.lookfor.yanaorental.models.auth.PasswordResetToken;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.repositories.auth.PasswordResetTokenRepository;
import com.lookfor.yanaorental.services.auth.PasswordResetTokenService;
import com.lookfor.yanaorental.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    @TransactionRequired
    public PasswordResetToken createToken(User user) {
        String token = TokenUtils.generateUUID();
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        return passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    @TransactionReadOnly
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Password reset token not found"));
    }

    @Override
    @TransactionReadOnly
    public boolean existsByToken(String token) {
        return passwordResetTokenRepository.existsByToken(token);
    }

    @Override
    @TransactionRequired
    public void deleteByToken(PasswordResetToken token) {
        passwordResetTokenRepository.delete(token);
    }
}
