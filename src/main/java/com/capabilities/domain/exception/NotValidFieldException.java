package com.capabilities.domain.exception;

public class NotValidFieldException extends RuntimeException {
    public NotValidFieldException(String message) {
        super(message);
    }
}