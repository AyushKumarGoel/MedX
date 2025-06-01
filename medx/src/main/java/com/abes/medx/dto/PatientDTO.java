package com.abes.medx.dto;

/**
 * Data Transfer Object (DTO) representing a Patient.
 * Extends UserDTO to include common user details and adds patient-specific fields.
 */
public class PatientDTO extends UserDTO {

    String patientId;


    public PatientDTO(String patientId, String name, String email, String password, String phoneNumber, String age) {
        super(name, email, password, phoneNumber, age);
        setPatientId(patientId);
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
                ", firstName='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", age='" + getAge() + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                      // Check same object reference
        if (!(o instanceof PatientDTO)) return false;   // Check class type
        if (!super.equals(o)) return false;              // Check superclass fields

        PatientDTO patient = (PatientDTO) o;

        return patientId != null ? patientId.equals(patient.patientId) : patient.patientId == null;
    }


    @Override
    public int hashCode() {
        int result = super.hashCode();                    // Include hash from superclass
        result = 31 * result + (patientId != null ? patientId.hashCode() : 0); // Include patientId
        return result;
    }
}
