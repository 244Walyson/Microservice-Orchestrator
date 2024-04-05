package br.com.microservices.orchestrated.orderservice.config.exceptions;


public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
