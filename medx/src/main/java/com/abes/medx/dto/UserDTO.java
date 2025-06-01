package com.abes.medx.dto;

/**
 * Data Transfer Object (DTO) representing a User.
 * This class holds common user attributes used by subclasses like PatientDTO, DoctorDTO, etc.
 */
public class UserDTO {
    // User's full name
    private String name;
    // User's email address
    private String email;
    // User's password (consider encrypting or hashing in real applications)
    private String password;
    // User's phone number
    private String phoneNumber;
    // User's age as a String (could be changed to int if numeric operations are needed)
    private String age;

    /**
     * Constructor to initialize a UserDTO object with all required fields.
     *
     * @param name        Full name of the user
     * @param email       Email address of the user
     * @param password    Password for the user account
     * @param phoneNumber Contact number of the user
     * @param age         Age of the user as a String
     */
    public UserDTO(String name, String email, String password, String phoneNumber, String age) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setAge(age);
    }

    // Setter for name
    public void setName(String name)  {
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for email
    public void setEmail(String email)  {
        this.email = email;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for password
    public void setPassword(String password)  {
        this.password = password;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for phoneNumber
    public void setPhoneNumber(String phoneNumber)  {
        this.phoneNumber = phoneNumber;
    }

    // Getter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter for age
    public void setAge(String age)  {
        this.age = age;
    }

    // Getter for age
    public String getAge() {
        return age;
    }

    /**
     * Returns a string representation of the UserDTO.
     *
     * @return String containing name, email, phone number, and age
     */
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
