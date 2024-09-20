package com.capabilities.domain.exception;

public class UserEntityNotUpdatedException extends RuntimeException {
    public UserEntityNotUpdatedException(String message) {
        super(message);
    }
}