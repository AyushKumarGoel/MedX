package com.abes.medx.dto;

public class UserDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String age;

    public UserDTO(String name, String email, String phoneNumber, String age) {
        getName();
        getEmail();
        getPhoneNumber();
        getAge();
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age='" + age + '\'' +
                '}';
    }


}
