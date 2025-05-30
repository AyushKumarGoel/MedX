package com.abes.medx.dto;

import com.abes.medx.exception.UserException;
import com.abes.medx.util.ValidationUtil;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String age;

    public UserDTO(String name, String email, String password, String phoneNumber, String age) throws UserException {
        setName(name);
        setEmail(email);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setAge(age);
    }

    public void setName(String name) throws UserException {
        this.name = ValidationUtil.validateName(name);
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) throws UserException {
        this.email = ValidationUtil.validateEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) throws UserException {
        this.password = ValidationUtil.validatePassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(String phoneNumber) throws UserException {
        this.phoneNumber = ValidationUtil.validatePhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAge(String age) throws UserException {
        this.age = ValidationUtil.validateAge(age);
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "UserDTO { " +
               "Name = '" + name + '\'' +
               ", Email = '" + email + '\'' +
               ", Phone = '" + phoneNumber + '\'' +
               ", Age = '" + age + '\'' +
               " }";
    }
}
