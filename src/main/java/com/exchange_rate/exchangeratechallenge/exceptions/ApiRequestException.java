package com.exchange_rate.exchangeratechallenge.exceptions;
public class ApiRequestException extends RuntimeException{
    public ApiRequestException(String message){
        super(message);
    }
}
