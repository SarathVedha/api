package com.vedha.exception;

public class ApiNotFoundException extends RuntimeException{

    public ApiNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
