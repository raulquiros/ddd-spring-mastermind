package com.buttons.mastermind.domain.exception;

public class OvercomeAttemptsException extends RuntimeException {

    private static final String MESSAGE = "HAS OVERCAME THE ATTEMPTS";

    public OvercomeAttemptsException() {
        super(MESSAGE);
    }
}

