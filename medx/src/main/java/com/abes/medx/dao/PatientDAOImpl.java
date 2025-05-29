package com.abes.medx.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.util.CollectionUtil;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public String getPatientId(String firstName, String lastName) {
        return CollectionUtil.patientMap.values().stream()
                .filter(p -> {
                    String name = p.getName().toLowerCase();
                    return name.contains(firstName.toLowerCase()) && name.contains(lastName.toLowerCase());
                })
                .map(PatientDTO::getPatientId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void bookAppointment(String patientId, String doctorId, String appointmentDate, String appointmentTime) {
        PatientDTO patient = CollectionUtil.patientMap.values().stream()
                .filter(p -> p.getPatientId().equals(patientId))
                .findFirst()
                .orElse(null);

        DoctorDTO doctor = CollectionUtil.doctorMap.get(doctorId);

        if (patient == null || doctor == null) {
            System.out.println("Invalid patient or doctor ID.");
            return;
        }

        String appointmentId = UUID.randomUUID().toString();
        AppointmentDTO appointment = new AppointmentDTO(
                appointmentId, appointmentDate, appointmentTime, patient, doctor, 500
        );

        CollectionUtil.appointmentMap.put(appointmentId, appointment);
        CollectionUtil.patientAppointments
                .computeIfAbsent(patientId, k -> new ArrayList<>())
                .add(appointmentId);
    }

    @Override
    public void cancelAppointment(String patientId, String appointmentId) {
        List<String> appointments = CollectionUtil.patientAppointments
                .getOrDefault(patientId, new ArrayList<>());

        if (appointments.contains(appointmentId)) {
            appointments.remove(appointmentId);
            CollectionUtil.appointmentMap.remove(appointmentId);
        } else {
            System.out.println("Appointment not found for this patient.");
        }
    }

    @Override
    public String getAppointmentDetails(String patientId, String appointmentId) {
        AppointmentDTO appointment = CollectionUtil.appointmentMap.get(appointmentId);

        if (appointment != null && appointment.getPatient().getPatientId().equals(patientId)) {
            return String.format("Appointment ID: %s\nDoctor: %s\nDate: %s\nTime: %s\nAmount Due: ₹%d",
                    appointment.getAppointmentId(),
                    appointment.getDoctor().getName(),
                    appointment.getAppointmentDate(),
                    appointment.getAppointmentTime(),
                    appointment.getToPay());
        }

        return "Appointment not found or does not belong to this patient.";
    }

    @Override
    public String getPatientHistory(String patientId) {
        List<String> appointments = CollectionUtil.patientAppointments
                .getOrDefault(patientId, new ArrayList<>());

        if (appointments.isEmpty()) {
            return "No appointments found for this patient.";
        }

        return appointments.stream()
                .map(CollectionUtil.appointmentMap::get)
                .filter(Objects::nonNull)
                .map(a -> String.format("ID: %s | Date: %s | Time: %s | Doctor: %s",
                        a.getAppointmentId(),
                        a.getAppointmentDate(),
                        a.getAppointmentTime(),
                        a.getDoctor().getName()))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void makePayment(String patientId, String paymentMethod, double amount) {
        System.out.printf("Payment of ₹%.2f received from patient %s via %s.%n", amount, patientId, paymentMethod);
    }
}
