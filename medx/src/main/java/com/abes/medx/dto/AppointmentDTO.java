package com.abes.medx.dto;

/**
 * Data Transfer Object (DTO) representing an Appointment in the system.
 * Contains details about appointment ID, date, time, patient, doctor, payment, and status.
 */
public class AppointmentDTO {
    // Unique identifier for the appointment
    String appointmentId;

    // Date of the appointment (e.g., "2025-06-01")
    String appointmentDate;

    // Time of the appointment (e.g., "14:30")
    String appointmentTime;

    // Patient involved in the appointment
    PatientDTO patient;

    // Doctor involved in the appointment
    DoctorDTO doctor;

    // Amount to be paid for the appointment
    int toPay;

    // Status of the appointment (default is "Scheduled")
    String status = "Scheduled";

    /**
     * Constructor to initialize an AppointmentDTO object with all necessary details.
     * The status is set to "Scheduled" by default regardless of the input parameter.
     * 
     * @param appointmentId   Unique ID for the appointment
     * @param appointmentDate Date of the appointment
     * @param appointmentTime Time of the appointment
     * @param patient        PatientDTO object representing the patient
     * @param doctor         DoctorDTO object representing the doctor
     * @param toPay          Amount to be paid
     * @param status         Status of the appointment (ignored, always set to "Scheduled")
     */
    public AppointmentDTO(String appointmentId, String appointmentDate, String appointmentTime, PatientDTO patient, DoctorDTO doctor, int toPay, String status) {
        setAppointmentId(appointmentId);
        setAppointmentDate(appointmentDate);
        setAppointmentTime(appointmentTime);
        setPatient(patient);
        setDoctor(doctor);
        setToPay(toPay);
        setStatus("Scheduled");  // Always sets status to "Scheduled" when creating new appointment
    }

    // Getter and Setter methods for each field

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }

    public int getToPay() {
        return toPay;
    }

    public void setToPay(int toPay) {
        this.toPay = toPay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the AppointmentDTO object,
     * including all its fields.
     *
     * @return String describing the appointment details
     */
    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "appointmentId='" + appointmentId + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", toPay=" + toPay +
                ", status='" + status + '\'' +
                '}';
    }
}
