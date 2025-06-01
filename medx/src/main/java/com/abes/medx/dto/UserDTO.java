package com.abes.medx.dto;

/**
 * Data Transfer Object (DTO) representing a User.
 * This class holds common user attributes used by subclasses like PatientDTO, DoctorDTO, etc.
 */
public class UserDTO {

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String age;


    public UserDTO(String name, String email, String password, String phoneNumber, String age) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setAge(age);
    }

    public void setName(String name)  {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email)  {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password)  {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(String phoneNumber)  {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAge(String age)  {
        this.age = age;
    }

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
