package com.moneybill.moneybill.exception;

import com.moneybill.moneybill.exception.access_denied.AccessDeniedException;
import com.moneybill.moneybill.exception.already_exists.AlreadyExistsException;
import com.moneybill.moneybill.exception.not_found.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected List<ValidationViolation> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException e
    ) {
        log.debug("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage());
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationViolation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    //400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleHttpMessageNotReadableException(
            final HttpMessageNotReadableException e
    ) {
        log.debug("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage());
        return new ErrorResponse(
                "Invalid HTTP request. Couldn't read it"
        );
    }

    //403
    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse handleAccessDeniedException(
            final AccessDeniedException e
    ) {
        log.debug("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    //404
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNotFoundException(
            final NotFoundException e
    ) {
        log.debug("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    //405
    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    protected ErrorResponse handleHttpRequestMethodNotSupportedException(
            final HttpRequestMethodNotSupportedException e
    ) {
        log.debug("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    //409
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleAlreadyExistsException(
            final AlreadyExistsException e
    ) {
        log.debug("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    // 500
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleRuntimeException(
            final RuntimeException e
    ) {
        log.debug("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return new ErrorResponse(
                e.getMessage()
        );
    }
}
