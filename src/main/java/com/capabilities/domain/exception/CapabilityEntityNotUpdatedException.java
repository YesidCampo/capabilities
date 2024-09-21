package com.capabilities.domain.exception;

public class CapabilityEntityNotUpdatedException extends RuntimeException {
    public CapabilityEntityNotUpdatedException(String message) {
        super(message);
    }
}