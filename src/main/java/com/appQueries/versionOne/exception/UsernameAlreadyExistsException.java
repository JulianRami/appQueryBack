package com.appQueries.versionOne.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}

