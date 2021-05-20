package com.lookfor.yanaorental.exceptions.auth;

import com.lookfor.yanaorental.exceptions.rest.AlreadyExistsException;

public class UserAlreadyExistsException extends AlreadyExistsException {
    static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
