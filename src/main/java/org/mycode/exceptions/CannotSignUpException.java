package org.mycode.exceptions;

public class CannotSignUpException extends RuntimeException {
    public CannotSignUpException() {
        super();
    }

    public CannotSignUpException(String message) {
        super(message);
    }
}
