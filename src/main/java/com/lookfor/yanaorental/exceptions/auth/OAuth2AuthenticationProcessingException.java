package com.lookfor.yanaorental.exceptions.auth;

import org.springframework.security.core.AuthenticationException;

public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    static final long serialVersionUID = 1L;

    public OAuth2AuthenticationProcessingException(String message) {
        super(message);
    }
}
