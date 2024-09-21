package com.capabilities.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CapabilityEntityNotFoundException.class)
    public Mono<ResponseEntity<String>> handleUserEntityNotFoundException(CapabilityEntityNotFoundException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

    @ExceptionHandler(CapabilityEntityNotCreatedException.class)
    public Mono<ResponseEntity<String>> handleUserEntityNotCreatedException(CapabilityEntityNotCreatedException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(CapabilityEntityNotUpdatedException.class)
    public Mono<ResponseEntity<String>> handleUserEntityNotUpdatedException(CapabilityEntityNotUpdatedException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(NotValidFieldException.class)
    public Mono<ResponseEntity<String>> handleEmptyFieldException(NotValidFieldException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(InvalidCapabilityException.class)
    public Mono<ResponseEntity<String>> handleUserEntityNotFoundException(InvalidCapabilityException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<String>> handleUserEntityNotFoundException(Exception ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }
}