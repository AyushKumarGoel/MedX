package com.abes.medx.dto;

/**
 * Data Transfer Object (DTO) class representing an Admin user.
 * Extends the UserDTO class to inherit common user attributes.
 */
public class AdminDTO extends UserDTO {

    // Unique identifier for the admin user
    private String adminId;

    /**
     * Constructor to initialize an AdminDTO object with all relevant fields.
     * 
     * @param adminId     Unique ID for the admin
     * @param name        Name of the admin
     * @param email       Email address of the admin
     * @param password    Password for admin authentication
     * @param phoneNumber Contact number of the admin
     * @param age         Age of the admin
     */
    public AdminDTO(String adminId, String name, String email, String password, String phoneNumber, String age) {
        super(name, email, password, phoneNumber, age);
        setAdminId(adminId);
    }

    /**
     * Gets the unique admin ID.
     * 
     * @return adminId The admin's unique identifier
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * Sets the unique admin ID.
     * 
     * @param adminId The admin ID to set
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    /**
     * Provides a string representation of the AdminDTO object,
     * including admin ID, name, email, phone number, and age.
     * 
     * @return String describing the admin details
     */
    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", firstName='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", age='" + getAge() + '\'' +
                '}';
    }
}
