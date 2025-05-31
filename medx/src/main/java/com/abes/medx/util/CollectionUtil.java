package com.abes.medx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;

public class CollectionUtil {

    public static final Map<String, AdminDTO> adminMap = new HashMap<>();
    public static final Map<String, PatientDTO> patientMap = new HashMap<>();
    public static final Map<String, DoctorDTO> doctorMap = new HashMap<>();
    public static final Map<String, AppointmentDTO> appointmentMap = new HashMap<>();
    public static final Map<String, List<String>> patientAppointments = new HashMap<>();
    public static final Map<String, List<String>> doctorAppointments = new HashMap<>();

    static {
        // Static Admin Data
        adminMap.put("ADM1", new AdminDTO("ADM1", "Ujjwal Kumar", "ujj@gmail.com", "ujj", "1234567890", "30"));
        adminMap.put("ADM2", new AdminDTO("ADM2", "Admin User", "admin@mail.com", "admin123", "1234567890", "35"));

        // Static Patient Data
        PatientDTO patient1 = new PatientDTO("P1", "RajVerma", "raj@gmail.com", "raj", "1234567890", "20");
        patientMap.put("P1", patient1);

        PatientDTO patient2 = new PatientDTO("P2", "Patient", "patient@mail.com", "patient123", "1234599990", "21");
        patientMap.put("P2", patient2);

        // Static Doctor Data
        DoctorDTO doctor1 = new DoctorDTO("D1", "Doctor", "doctor@mail.com", "doctor123", "1234567890", "29", "Cardiologist", 10);
        doctorMap.put("D1", doctor1);

        DoctorDTO doctor2 = new DoctorDTO("D2", "Rahul Sharma", "rahul@gmail.com", "rahul", "1234567890", "35", "Neurologist", 15);
        doctorMap.put("D2", doctor2);

        // Static Appointment Data
        AppointmentDTO ap1 = new AppointmentDTO("AP1", "2023-10-01", "10:00", patient1, doctor1, 500, "Scheduled");
        appointmentMap.put("AP1", ap1);

        AppointmentDTO ap2 = new AppointmentDTO("AP2", "2023-10-02", "11:00", patient2, doctor2, 700, "Scheduled");
        appointmentMap.put("AP2", ap2);
    }
}
