package com.buttons.mastermind.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidGameIdException extends RuntimeException {

    private static final String MESSAGE = "INVALID GAME ID";

    public InvalidGameIdException() {
        super(MESSAGE);
    }
}

