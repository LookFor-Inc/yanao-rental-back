package com.lookfor.yanaorental.exceptions.rest;

public class BadRequestException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }
}
