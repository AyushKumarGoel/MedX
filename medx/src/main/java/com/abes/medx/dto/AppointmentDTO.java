package com.abes.medx.dto;

public class AppointmentDTO {
    String appointmentId;
    String appointmentDate;
    String appointmentTime;
    Patient patient;
    DoctorDTO doctor;
    int toPay;

    public AppointmentDTO(String appointmentId, String appointmentDate, String appointmentTime, Patient patient, DoctorDTO doctor, int toPay) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.doctor = doctor;
        this.toPay = toPay;
    }

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
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

}
