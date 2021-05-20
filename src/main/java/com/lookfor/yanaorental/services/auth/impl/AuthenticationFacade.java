package com.lookfor.yanaorental.services.auth.impl;

import com.lookfor.yanaorental.exceptions.auth.InvalidTokenException;
import com.lookfor.yanaorental.services.auth.IAuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() throws InvalidTokenException {
        Long userId = (Long) getAuthentication().getPrincipal();
        if (userId == null) {
            throw new InvalidTokenException("User was not found with this token");
        }
        return userId;
    }
}
