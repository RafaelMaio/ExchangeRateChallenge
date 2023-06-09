package com.exchange_rate.exchangeratechallenge.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiException(
        @Schema(description = "Error message")String message,
        @Schema(description = "Http Status code")HttpStatus httpStatus,
        @Schema(description = "Timestamp")ZonedDateTime timestamp) {
}
