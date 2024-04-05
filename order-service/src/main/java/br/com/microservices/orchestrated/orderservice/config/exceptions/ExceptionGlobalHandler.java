package br.com.microservices.orchestrated.orderservice.config.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetails> handlerValidationException(ValidationException ex, HttpServletRequest request) {
        var ExceptionDetails = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionDetails);
    }
}
