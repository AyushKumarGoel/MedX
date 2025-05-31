package com.abes.medx.dto;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

// import com.abes.medx.exception.UserException;
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

    public void showProfile() {
        System.out.println("Admin Profile:");
        System.out.println("Admin ID: " + adminId);
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhoneNumber());
        System.out.println("Age: " + getAge());
    }

    public boolean removeDoctor(List<DoctorDTO> doctors, String doctorId) {
        try {
            Field doctorIdField = DoctorDTO.class.getDeclaredField("doctorId");
            doctorIdField.setAccessible(true);

            Iterator<DoctorDTO> iterator = doctors.iterator();
            while (iterator.hasNext()) {
                DoctorDTO doctor = iterator.next();
                Object idValue = doctorIdField.get(doctor);
                if (idValue != null && idValue.toString().equals(doctorId)) {
                    iterator.remove();
                    System.out.println("Doctor with ID " + doctorId + " removed.");
                    return true;
                }
            }

            System.out.println("Doctor with ID " + doctorId + " not found.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Reflection error: " + e.getMessage());
        }

        return false;
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