package com.example.hotel.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hotel.exception.ResourcenotFoundexception;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourcenotFoundexception.class)
    public ResponseEntity<APIError> handleResourceNotFound(ResourcenotFoundexception exception) {

        APIError apierror = new APIError(
                HttpStatus.NOT_FOUND, 
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apierror);
    }
}
