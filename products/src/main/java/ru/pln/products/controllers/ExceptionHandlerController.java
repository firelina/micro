package ru.pln.products.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import ru.pln.products.exception.APIException;
import ru.pln.products.exception.JsonException;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {
    @ExceptionHandler(APIException.class)
    public final ResponseEntity<JsonException> handleNoSuchElementException(APIException exception, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity<>(new JsonException(exception.getClass().getSimpleName(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<JsonException> handleAllExceptions(Exception exception, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity<>(new JsonException(exception.getClass().getSimpleName(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
