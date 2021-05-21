package com.lookfor.yanaorental.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }
}
