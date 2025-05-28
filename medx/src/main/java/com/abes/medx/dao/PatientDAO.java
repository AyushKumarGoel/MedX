package com.abes.medx.dao;


public interface PatientDAO {
    public String getPatientId(String firstName, String lastName);

    public void bookAppointment(String patientId, String doctorId, String appointmentDate, String appointmentTime);

    public void cancelAppointment(String patientId, String appointmentId);

    public String getAppointmentDetails(String patientId, String appointmentId);

    public String getPatientHistory(String patientId);

    public void makePayment(String patientId, String paymentMethod, double amount);
    
}