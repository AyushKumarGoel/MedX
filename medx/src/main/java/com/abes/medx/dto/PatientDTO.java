package com.abes.medx.dto;

import com.abes.medx.dto.UserDTO;

public class PatientDTO extends UserDTO{
    String patientId;

    public PatientDTO(String firstName, String lastName, String email, String phoneNumber, String age, String patientId) {
        super(firstName, lastName, email, phoneNumber, age);
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", age='" + getAge() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientDTO)) return false;
        if (!super.equals(o)) return false;

        PatientDTO patient = (PatientDTO) o;

        return patientId != null ? patientId.equals(patient.patientId) : patient.patientId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (patientId != null ? patientId.hashCode() : 0);
        return result;
    }

}