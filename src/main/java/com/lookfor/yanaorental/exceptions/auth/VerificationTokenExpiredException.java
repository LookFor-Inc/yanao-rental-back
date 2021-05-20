package com.lookfor.yanaorental.exceptions.auth;

import com.lookfor.yanaorental.exceptions.rest.UnauthorizedException;

public class VerificationTokenExpiredException extends UnauthorizedException {
    static final long serialVersionUID = 1L;

    public VerificationTokenExpiredException() {
        super("Verification token expired");
    }
}
