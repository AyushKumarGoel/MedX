package com.abes.medx.dto;

/**
 * Data Transfer Object (DTO) representing a Patient.
 * Extends UserDTO to include common user details and adds patient-specific fields.
 */
public class PatientDTO extends UserDTO {

    // Unique identifier for the patient
    String patientId;

    /**
     * Constructor to initialize a PatientDTO object with all details.
     * Calls superclass constructor to set common user fields,
     * then sets the patient-specific ID.
     *
     * @param patientId   Unique ID of the patient
     * @param name        Patient's name
     * @param email       Patient's email address
     * @param password    Patient's password
     * @param phoneNumber Patient's contact number
     * @param age         Patient's age
     */
    public PatientDTO(String patientId, String name, String email, String password, String phoneNumber, String age) {
        super(name, email, password, phoneNumber, age);
        setPatientId(patientId);
    }

    // Getter for patientId
    public String getPatientId() {
        return patientId;
    }

    // Setter for patientId
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Returns a string representation of the PatientDTO,
     * including patientId and inherited user details.
     *
     * @return String representation of the patient
     */
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

    /**
     * Overrides equals() to compare two PatientDTO objects.
     * Two patients are equal if their IDs and inherited user fields are equal.
     *
     * @param o Object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                      // Check same object reference
        if (!(o instanceof PatientDTO)) return false;   // Check class type
        if (!super.equals(o)) return false;              // Check superclass fields

        PatientDTO patient = (PatientDTO) o;

        // Compare patientId, handling nulls safely
        return patientId != null ? patientId.equals(patient.patientId) : patient.patientId == null;
    }

    /**
     * Generates hash code consistent with equals().
     *
     * @return hash code for the PatientDTO object
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();                    // Include hash from superclass
        result = 31 * result + (patientId != null ? patientId.hashCode() : 0); // Include patientId
        return result;
    }
}
