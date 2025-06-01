package com.abes.medx.exception;

/**
 * Custom exception class for handling booking-related errors
 * in the MedX application.
 * 
 * This exception can be thrown when issues occur during the booking process,
 * such as invalid booking details, booking conflicts, or failures.
 */
public class BookingException extends Exception {

    /**
     * Constructs a new BookingException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public BookingException(String message) {
        super(message);
    }
}