package com.abes.medx.dto;
public class AdminDTO extends UserDTO {

    private String adminId;

    public AdminDTO(String adminId, String name, String email, String password , String phoneNumber, String age) {
        super(name, email, password, phoneNumber, age);
        setAdminId(adminId);
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }


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