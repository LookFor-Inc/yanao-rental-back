package com.lookfor.yanaorental.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserIsNotInRentalException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public UserIsNotInRentalException(String message) {
        super(message);
    }
}
