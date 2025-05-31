package com.abes.medx.exception;

public class MedicalException extends Exception {
    public MedicalException(String message) {
        super(message);
    }

    public MedicalException(String message, Throwable cause) {
        super(message, cause);
    }
}