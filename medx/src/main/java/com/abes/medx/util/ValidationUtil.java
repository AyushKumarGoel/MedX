package com.abes.medx.util;

import com.abes.medx.exception.UserException;

public class ValidationUtil {

    public static String validateName(String name) throws UserException {
        if (name == null || name.trim().isEmpty()) {
            throw new UserException("Name cannot be empty.");
        }
        name = name.trim();
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            throw new UserException("Name must contain only alphabetic characters and spaces. Provided: " + name);
        }
        return name;
    }

    public static String validateEmail(String email) throws UserException {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty.");
        }
        email = email.trim();
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            throw new UserException("Invalid email format: " + email);
        }
        return email;
    }

    public static String validatePassword(String password) throws UserException {
        if (password == null || password.trim().isEmpty()) {
            throw new UserException("Password cannot be empty.");
        }
        return password.trim();
    }

    public static String validatePhoneNumber(String phoneNumber) throws UserException {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new UserException("Phone number cannot be empty.");
        }
        phoneNumber = phoneNumber.trim();
        if (!phoneNumber.matches("^\\d{10}$")) {
            throw new UserException("Phone number must be exactly 10 digits. Provided: " + phoneNumber);
        }
        return phoneNumber;
    }

    public static String validateAge(String age) throws UserException {
        if (age == null || age.trim().isEmpty()) {
            throw new UserException("Age cannot be empty.");
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
}
