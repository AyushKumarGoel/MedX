package com.abes.medx.util;

import org.junit.jupiter.api.Test;

import com.abes.medx.exception.UserException;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilTest {
    
    @Test
    public void testValidateName_Valid() throws UserException {
        String name = "John Doe";
        String result = ValidationUtil.validateName(name);
        assertEquals("John Doe", result);
    }

    @Test
    public void testValidateName_Empty() {
        assertThrows(UserException.class, () -> ValidationUtil.validateName(""));
    }

    @Test
    public void testValidateName_InvalidCharacters() {
        assertThrows(UserException.class, () -> ValidationUtil.validateName("John123"));
    }

    @Test
    public void testValidateEmail_Valid() throws UserException {
        String email = "user@mail.com";
        String result = ValidationUtil.validateEmail(email);
        assertEquals("user@mail.com", result);
    }

    @Test
    public void testValidateEmail_Empty() {
        assertThrows(UserException.class, () -> ValidationUtil.validateEmail(""));
    }

    @Test
    public void testValidateEmail_InvalidFormat() {
        assertThrows(UserException.class, () -> ValidationUtil.validateEmail("user@mail"));
    }

    @Test
    public void testValidatePassword_Valid() throws UserException {
        String password = "securePassword";
        String result = ValidationUtil.validatePassword(password);
        assertEquals("securePassword", result);
    }

    @Test
    public void testValidatePassword_Empty() {
        assertThrows(UserException.class, () -> ValidationUtil.validatePassword(""));
    }

    @Test
    public void testValidatePhoneNumber_Valid() throws UserException {
        String phoneNumber = "1234567890";
        String result = ValidationUtil.validatePhoneNumber(phoneNumber);
        assertEquals("1234567890", result);
    }

    @Test
    public void testValidatePhoneNumber_Empty() {
        assertThrows(UserException.class, () -> ValidationUtil.validatePhoneNumber(""));
    }

    @Test
    public void testValidatePhoneNumber_InvalidLength() {
        assertThrows(UserException.class, () -> ValidationUtil.validatePhoneNumber("12345"));
    }

    @Test
    public void testValidateAge_Valid() throws UserException {
        String age = "30";
        String result = ValidationUtil.validateAge(age);
        assertEquals("30", result);
    }

    @Test
    public void testValidateAge_Empty() {
        assertThrows(UserException.class, () -> ValidationUtil.validateAge(""));
    }

    @Test
    public void testValidateAge_InvalidNumber() {
        assertThrows(UserException.class, () -> ValidationUtil.validateAge("abc"));
    }

    @Test
    public void testValidateAge_OutOfBounds() {
        assertThrows(UserException.class, () -> ValidationUtil.validateAge("150"));
    }

    @Test
    public void testValidateAge_Negative() {
        assertThrows(UserException.class, () -> ValidationUtil.validateAge("-5"));
    }

}
