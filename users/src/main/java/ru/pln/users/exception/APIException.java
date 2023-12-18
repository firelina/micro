package ru.pln.users.exception;

public class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }
}
