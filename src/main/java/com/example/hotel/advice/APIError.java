package com.example.hotel.advice;

import org.springframework.http.HttpStatus;

public class APIError {

    private HttpStatus status;
    private String message;

    public APIError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
