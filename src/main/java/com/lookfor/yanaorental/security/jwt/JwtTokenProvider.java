package com.lookfor.yanaorental.security.jwt;

import org.springframework.security.core.Authentication;

public interface JwtTokenProvider {
    String generateToken(Authentication authentication);

    boolean validateToken(String token);

    Authentication getAuthenticationFromToken(String token);
}
