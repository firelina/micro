package ru.pln.orders.exception;

public class APIException extends RuntimeException{
    public APIException(String message) {
        super(message);
    }
}
