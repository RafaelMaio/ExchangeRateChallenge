package com.exchange_rate.exchangeratechallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiException> handleApiException(ApiRequestException e){
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiException>  handleMissingParams(MissingServletRequestParameterException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiException  >  handleWrongType(MethodArgumentTypeMismatchException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}