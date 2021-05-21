package com.lookfor.yanaorental.exceptions.rest;

public class ConflictException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public ConflictException(String message) {
        super(message);
    }
}
