package com.buttons.mastermind.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidColorsException extends RuntimeException {

    private static final String MESSAGE = "INVALID COLORS";

    public InvalidColorsException() {
        super(MESSAGE);
    }
}

