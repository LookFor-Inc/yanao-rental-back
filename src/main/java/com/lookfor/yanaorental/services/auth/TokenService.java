package com.lookfor.yanaorental.services.auth;

import com.lookfor.yanaorental.exceptions.auth.VerificationTokenExpiredException;
import com.lookfor.yanaorental.models.auth.BaseToken;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.utils.DateUtils;

public interface TokenService<T extends BaseToken> {
    T createToken(User user);

    default T regenerateToken(T existingToken) {
        return null;
    }

    T findByToken(String token);

    default boolean existsByToken(String token) {
        return false;
    }

    default void verifyExpiration(T token) {
        if (token.getExpiryDate().compareTo(DateUtils.getNow()) < 0) {
            throw new VerificationTokenExpiredException();
        }
    }

    void deleteByToken(T token);
}
