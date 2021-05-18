package com.lookfor.yanaorental.exceptions;

public class NotFoundException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }
}
