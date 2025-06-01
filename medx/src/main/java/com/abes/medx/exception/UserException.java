package com.abes.medx.exception;

/**
 * Custom exception class for handling user-related errors
 * within the MedX application.
 * 
 * This exception is used to indicate problems such as invalid user
 * data, authentication failures, or other user-specific issues.
 */
public class UserException extends Exception {

    /**
     * Constructs a new UserException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public UserException(String message) {
        super(message);
    }
}
