package com.lookfor.yanaorental.exceptions.rest;

public class UserIsNotInRentalException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public UserIsNotInRentalException(String message) {
        super(message);
    }
}
