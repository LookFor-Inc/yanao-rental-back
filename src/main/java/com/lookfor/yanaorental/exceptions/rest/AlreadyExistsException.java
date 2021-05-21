package com.lookfor.yanaorental.exceptions.rest;

public class AlreadyExistsException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public AlreadyExistsException(String message) {
        super(message);
    }
}
