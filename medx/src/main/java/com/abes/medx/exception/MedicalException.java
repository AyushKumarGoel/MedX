package com.abes.medx.exception;

/**
 * Custom exception class for handling medical-related errors
 * within the MedX application.
 * 
 * This exception can be used to signal various medical domain
 * issues such as invalid data, processing errors, or unexpected conditions.
 */
public class MedicalException extends Exception {

    /**
     * Constructs a new MedicalException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public MedicalException(String message) {
        super(message);
    }

    /**
     * Constructs a new MedicalException with the specified detail message
     * and cause of the exception.
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause the underlying cause of the exception (can be retrieved later)
     */
    public MedicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
