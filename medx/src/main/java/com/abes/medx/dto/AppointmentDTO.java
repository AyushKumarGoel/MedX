package com.abes.medx.dto;

public class AppointmentDTO {
    String appointmentId;
    String appointmentDate;
    String appointmentTime;
    PatientDTO patient;
    DoctorDTO doctor;
    int toPay;
    String status = "Scheduled";

    public AppointmentDTO(String appointmentId, String appointmentDate, String appointmentTime, PatientDTO patient, DoctorDTO doctor, int toPay, String status) {
        setAppointmentId(appointmentId);
        setAppointmentDate(appointmentDate);
        setAppointmentTime(appointmentTime);
        setPatient(patient);
        setDoctor(doctor);
        setToPay(toPay);
        setStatus("Scheduled");
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
