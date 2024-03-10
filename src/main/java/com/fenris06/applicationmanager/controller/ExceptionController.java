package com.fenris06.applicationmanager.controller;


import com.fenris06.applicationmanager.exception.ArgumentException;
import com.fenris06.applicationmanager.exception.DataValidationException;
import com.fenris06.applicationmanager.exception.ErrorResponse;
import com.fenris06.applicationmanager.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class ExceptionController {
    //TODO Не забыть добавить контроллер тровабле

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundHandler(final NotFoundException e) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                "The required object was not found.",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFormatHandler(final NumberFormatException e) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                "Incorrectly made request.",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse emailValidException(final MethodArgumentNotValidException e) {
        FieldError error = e.getFieldError();
        if (error == null) {
            return new ErrorResponse(
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    "Incorrectly made request.",
                    e.getMessage(),
                    LocalDateTime.now()
            );
        } else {
            return new ErrorResponse(
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    "Incorrectly made request.",
                    error.getDefaultMessage(),
                    LocalDateTime.now()
            );
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAll(ConstraintViolationException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Incorrectly made request.",
                e.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse constraintHandler(final DataIntegrityViolationException e) {
        return new ErrorResponse(
                HttpStatus.CONFLICT.getReasonPhrase(),
                "Integrity constraint has been violated.",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse dateHandler(final DataValidationException e) {
        return new ErrorResponse(
                "FORBIDDEN",
                "For the requested operation the conditions are not met.",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse argumentHandler(final ArgumentException e) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Incorrectly made request.",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

}
