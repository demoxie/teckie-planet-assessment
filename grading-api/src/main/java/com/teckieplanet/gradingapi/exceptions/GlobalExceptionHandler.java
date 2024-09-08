package com.teckieplanet.gradingapi.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorDetails> handleAPIException(APIException ex, HttpServletRequest request) {
        String transactionId = request.getHeader("transactionId");
        String method = request.getMethod();
        String path = request.getRequestURI();
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .error(ex.getLocalizedMessage())
                .path(path)
                .timestamp(LocalDateTime.now().toString())
                .statusCode(ex.getStatusCode())
                .method(method)
                .transactionId(transactionId)
                .build();
        return ResponseEntity.status(ex.getStatusCode()).body(errorDetails);
    }
}
