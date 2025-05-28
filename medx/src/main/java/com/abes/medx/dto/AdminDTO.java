package main.java.com.abes.medx.dto;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class AdminDTO extends User {

    private String adminId;
    private String password;

    public AdminDTO(String firstName, String lastName, String email, String phoneNumber, String age,
                 String adminId, String password) {
        super(firstName, lastName, email, phoneNumber, age);
        this.adminId = adminId;
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void showProfile() {
        System.out.println("Admin Profile:");
        System.out.println("Admin ID: " + adminId);
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhoneNumber());
        System.out.println("Age: " + getAge());
    }

    public boolean removeDoctor(List<Doctor> doctors, String doctorId) {
        try {
            Field doctorIdField = Doctor.class.getDeclaredField("doctorId");
            doctorIdField.setAccessible(true);

            Iterator<Doctor> iterator = doctors.iterator();
            while (iterator.hasNext()) {
                Doctor doctor = iterator.next();
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
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", age='" + getAge() + '\'' +
                '}';
    }
}
