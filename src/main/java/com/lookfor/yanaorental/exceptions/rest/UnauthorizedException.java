package com.lookfor.yanaorental.exceptions.rest;

public class UnauthorizedException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public UnauthorizedException(String message) {
        super(message);
    }
}
