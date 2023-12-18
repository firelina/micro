package ru.pln.products.exception;

public class APIException extends RuntimeException{
    public APIException(String message) {
        super(message);
    }
}
