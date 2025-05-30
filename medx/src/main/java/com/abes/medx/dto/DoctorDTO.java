package com.abes.medx.dto;

import java.util.Objects;

import com.abes.medx.exception.UserException;
public class DoctorDTO extends UserDTO {

    private String doctorId;
    private String specialization;
    private int yearsOfExperience;

    public DoctorDTO(String doctorId, String name, String email, String password, String phoneNumber, String age,
                    String specialization, int yearsOfExperience) throws UserException {
        super(name, email, password, phoneNumber, age);
        setDoctorId(doctorId);
        setSpecialization(specialization);
        setYearsOfExperience(yearsOfExperience);
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String id) {
        this.doctorId = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoctorDTO)) return false;
        if (!super.equals(o)) return false;
        DoctorDTO that = (DoctorDTO) o;
        return doctorId == that.doctorId &&
                yearsOfExperience == that.yearsOfExperience &&
                Objects.equals(specialization, that.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), doctorId, specialization, yearsOfExperience);
    }

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