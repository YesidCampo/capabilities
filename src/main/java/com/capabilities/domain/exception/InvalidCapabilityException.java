package com.capabilities.domain.exception;

public class InvalidCapabilityException extends RuntimeException {
    public InvalidCapabilityException(String message) {
        super(message);
    }
}