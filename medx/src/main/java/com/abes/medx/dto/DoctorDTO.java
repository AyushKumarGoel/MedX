package com.abes.medx.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) representing a Doctor.
 * Extends UserDTO to include common user details and adds doctor-specific fields.
 */
public class DoctorDTO extends UserDTO {

    // Unique identifier for the doctor
    private String doctorId;

    // Doctor's medical specialization (e.g., Cardiologist, Dermatologist)
    private String specialization;

    // Number of years the doctor has been practicing
    private int yearsOfExperience;

    /**
     * Constructor to initialize a DoctorDTO object with all details.
     * It calls the superclass constructor to set common user fields,
     * then sets doctor-specific fields.
     * Also prints the details to the console when creating an instance.
     *
     * @param doctorId          Unique ID of the doctor
     * @param name              Doctor's name
     * @param email             Doctor's email address
     * @param password          Doctor's password
     * @param phoneNumber       Doctor's contact number
     * @param age               Doctor's age
     * @param specialization    Doctor's field of specialization
     * @param yearsOfExperience Number of years of experience
     */
    public DoctorDTO(String doctorId, String name, String email, String password, String phoneNumber, String age,
                    String specialization, int yearsOfExperience) {
        super(name, email, password, phoneNumber, age);
        System.out.println("Creating DoctorDTO: ID=" + doctorId + ", Name=" + name + ", Email=" + email +
                          ", Phone=" + phoneNumber + ", Age=" + age + ", Specialization=" + specialization +
                          ", Experience=" + yearsOfExperience);
        setDoctorId(doctorId);
        setSpecialization(specialization);
        setYearsOfExperience(yearsOfExperience);
    }

    // Getter and setter methods for doctorId
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String id) {
        this.doctorId = id;
    }

    // Getter and setter methods for specialization
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Getter and setter methods for yearsOfExperience
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Overrides equals() method to compare two DoctorDTO objects.
     * Two doctors are considered equal if their IDs, specialization, years of experience, 
     * and user fields (inherited) are equal.
     *
     * @param o Object to compare with
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoctorDTO)) return false;
        if (!super.equals(o)) return false;
        DoctorDTO that = (DoctorDTO) o;
        return Objects.equals(doctorId, that.doctorId) &&
               yearsOfExperience == that.yearsOfExperience &&
               Objects.equals(specialization, that.specialization);
    }

    /**
     * Overrides hashCode() method to generate hash code consistent with equals.
     *
     * @return int hash code of the DoctorDTO object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), doctorId, specialization, yearsOfExperience);
    }

    /**
     * Provides string representation of the DoctorDTO object,
     * including all relevant fields.
     *
     * @return String describing the doctor details
     */
    @Override
    public String toString() {
        return "DoctorDTO{" +
                "doctorId=" + doctorId +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhoneNumber() + '\'' +
                ", age='" + getAge() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experience=" + yearsOfExperience +
                '}';
    }
}
// End of DoctorDTO.java
// This class represents a Doctor in the MedX system, encapsulating all necessary details