package com.abes.medx.util;

import com.abes.medx.exception.UserException;

public class ValidationUtil {

    /**
     * Validates the provided name.
     * @param name User's name input.
     * @return Trimmed name if valid.
     * @throws UserException if name is null, empty, or contains invalid characters.
     */
    public static String validateName(String name) throws UserException {
        if (name == null || name.trim().isEmpty()) {
            throw new UserException("Name cannot be empty. Provided: " + name);
        }
        name = name.trim();
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            throw new UserException("Name must contain only alphabetic characters and spaces. Provided: " + name);
        }
        return name;
    }

    /**
     * Validates the provided email address.
     * @param email User's email input.
     * @return Trimmed email if valid.
     * @throws UserException if email is null, empty, or not in valid format.
     */
    public static String validateEmail(String email) throws UserException {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty. Provided: " + email);
        }
        email = email.trim();
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            throw new UserException("Invalid email format. Provided: " + email);
        }
        return email;
    }

    /**
     * Validates the provided password.
     * @param password User's password input.
     * @return Trimmed password if valid.
     * @throws UserException if password is null or empty.
     */
    public static String validatePassword(String password) throws UserException {
        if (password == null || password.trim().isEmpty()) {
            throw new UserException("Password cannot be empty. Provided: " + password);
        }
        return password.trim();
    }

    /**
     * Validates the provided phone number.
     * @param phoneNumber User's phone number input.
     * @return Trimmed phone number if valid.
     * @throws UserException if phone number is null, empty, or not exactly 10 digits.
     */
    public static String validatePhoneNumber(String phoneNumber) throws UserException {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new UserException("Phone number cannot be empty. Provided: " + phoneNumber);
        }
        phoneNumber = phoneNumber.trim();
        if (!phoneNumber.matches("^\\d{10}$")) {
            throw new UserException("Phone number must be exactly 10 digits. Provided: " + phoneNumber);
        }
        return phoneNumber;
    }

    /**
     * Validates the provided age.
     * @param age User's age input as string.
     * @return Trimmed age string if valid.
     * @throws UserException if age is null, empty, non-numeric, or out of realistic bounds (0–120).
     */
    public static String validateAge(String age) throws UserException {
        if (age == null || age.trim().isEmpty()) {
            throw new UserException("Age cannot be empty. Provided: " + age);
        }
        age = age.trim();
        try {
            int ageNum = Integer.parseInt(age);
            if (ageNum < 0 || ageNum > 120) {
                throw new UserException("Age must be between 0 and 120. Provided: " + age);
            }
        } catch (NumberFormatException e) {
            throw new UserException("Age must be a valid number. Provided: " + age);
        }
        return age;
    }

    /**
     * Validates the provided time in HH:MM 24-hour format.
     * @param time Time input as string.
     * @return Valid time string.
     * @throws AppointmentException if time is null or in incorrect format.
     */
    public static String validateTime(String time) throws com.abes.medx.exception.AppointmentException {
        if (time == null || !time.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
            throw new com.abes.medx.exception.AppointmentException("Invalid time format. Please use HH:MM (24-hour format).");
        }
        return time;
    }

    /**
     * Validates the provided date in YYYY-MM-DD format.
     * @param date Date input as string.
     * @return Valid date string.
     * @throws AppointmentException if date is null or in incorrect format.
     */
    public static String validateDate(String date) throws com.abes.medx.exception.AppointmentException {
        if (date == null || !date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new com.abes.medx.exception.AppointmentException("Invalid date format. Please use YYYY-MM-DD.");
        }
        return date;
    }
}
// End of ValidationUtil.java
// This utility class provides methods to validate user inputs such as name, email, password, phone number, age, time, and date.