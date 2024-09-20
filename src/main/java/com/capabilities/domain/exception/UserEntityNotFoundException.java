package com.capabilities.domain.exception;

public class UserEntityNotFoundException extends RuntimeException {
    public UserEntityNotFoundException(String message) {
        super(message);
    }
}