package com.lookfor.yanaorental.exceptions.auth;

import com.lookfor.yanaorental.exceptions.rest.NotFoundException;

public class InvalidTokenException extends NotFoundException {
    static final long serialVersionUID = 1L;

    public InvalidTokenException(String message) {
        super(message);
    }
}
