package com.lookfor.yanaorental.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public AlreadyExistsException(String message) {
        super(message);
    }
}
