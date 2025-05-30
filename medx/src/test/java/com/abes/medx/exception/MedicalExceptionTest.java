package com.abes.medx.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class MedicalExceptionTest {

    @Test
    void testMedicalExceptionWithMessage() {
        MedicalException exception = new MedicalException("Test error message");
        assertEquals("Test error message", exception.getMessage(), "Exception message should match");
        assertNull(exception.getCause(), "Cause should be null when not provided");
    }

    @Test
    void testMedicalExceptionWithMessageAndCause() {
        Throwable cause = new RuntimeException("Root cause");
        MedicalException exception = new MedicalException("Test error with cause", cause);
        assertEquals("Test error with cause", exception.getMessage(), "Exception message should match");
        assertNotNull(exception.getCause(), "Cause should not be null");
        assertEquals(cause, exception.getCause(), "Cause should match provided throwable");
    }
}