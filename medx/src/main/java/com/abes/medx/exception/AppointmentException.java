package com.abes.medx.exception;

/**
 * Custom exception class for handling errors related to appointments
 * in the MedX application.
 * 
 * This exception can be thrown when appointment-specific issues occur,
 * such as scheduling conflicts, invalid appointment data, or other
 * appointment-related errors.
 */
public class AppointmentException extends Exception {

    /**
     * Constructs a new AppointmentException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public AppointmentException(String message) {
        super(message);
    }
}
//      * @return true if the appointment was successfully marked as completed.
//      * @throws AppointmentException if marking as completed fails.