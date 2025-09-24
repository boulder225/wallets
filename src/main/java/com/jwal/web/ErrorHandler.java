package com.jwal.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {
    private static HttpHeaders NO_STORE_HEADERS = noStore();

    private static HttpHeaders noStore() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CACHE_CONTROL, "no-store");
        return headers;
    }

    private static ResponseEntity<Object> body(HttpStatus status, String code, String message) {
        Map<String, Object> payload = Map.of("code", code, "message", message);
        return ResponseEntity.status(status).headers(NO_STORE_HEADERS).contentType(MediaType.APPLICATION_JSON).body(payload);
    }

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }

    public static class ConflictException extends RuntimeException {
        public ConflictException(String message) {
            super(message);
        }
    }


}
