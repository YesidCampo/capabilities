package com.capabilities.domain.exception;

public class UserEntityNotCreatedException extends RuntimeException {
    public UserEntityNotCreatedException(String message) {
        super(message);
    }
}