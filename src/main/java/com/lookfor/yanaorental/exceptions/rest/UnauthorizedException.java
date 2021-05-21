package com.lookfor.yanaorental.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public UnauthorizedException(String message) {
        super(message);
    }
}
