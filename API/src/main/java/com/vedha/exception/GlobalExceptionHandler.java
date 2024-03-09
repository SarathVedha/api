package com.vedha.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiNotFoundException.class)
    public ResponseEntity<HashMap<String, Object>> apiNotFoundException(ApiNotFoundException apiNotFoundException) {

        HashMap<String, Object> error = new HashMap<>();
        error.put("error", apiNotFoundException.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(JavaScriptEncDecException.class)
    public ResponseEntity<HashMap<String, Object>> encryptDecryptException(JavaScriptEncDecException javaScriptEncDecException) {

        HashMap<String, Object> error = new HashMap<>();
        error.put("error", javaScriptEncDecException.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap<String, Object>> globalException(Exception exception) {

        HashMap<String, Object> error = new HashMap<>();
        error.put("error", exception.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
}
