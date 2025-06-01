package com.abes.medx.dto;

/**
 * Data Transfer Object (DTO) class representing an Admin user.
 * Inherits common user attributes from UserDTO.
 */
public class AdminDTO extends UserDTO {

    private String adminId;

    /**
     * Initializes an AdminDTO with the provided details.
     */
    public AdminDTO(String adminId, String name, String email, String password, String phoneNumber, String age) {
        super(name, email, password, phoneNumber, age);
        setAdminId(adminId);
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    /**
     * Returns a string representation of the AdminDTO.
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
