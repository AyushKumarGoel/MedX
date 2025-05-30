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
        // Static Patient Data
        patientMap.put("P001", new PatientDTO("P001", "Raj Verma", "raj@gmail.com", "raj", "1234567890", "20"));
        patientMap.put("P002", new PatientDTO("P002", "Yatharth Yadav", "yath@gmail.com", "yatharth", "1234599990", "21"));

        // Static Doctor Data
        doctorMap.put("D001", new DoctorDTO("D001" ,"Dr. Anjali Singh", "anjali@gmail.com", "anjali", "1234567890", "29", "Cardiologist", 10));
        doctorMap.put("D002", new DoctorDTO("D002" ,"Dr. Rahul Sharma", "rahul", "rahul", "1234567890", "35", "Neurologist", 15));

        // Static Admin Data
        adminMap.put("A001", new AdminDTO("A001", "Ujj", "ujj@gmail.com", "ujj", "1234567890", "30"));

        // Static Appointment Data
    //     appointmentMap.put("AP001", new AppointmentDTO("AP001", "2023-10-01", "10:00", patientMap.get("P001"), doctorMap.get("D001"), 500, "Scheduled"));
    //     appointmentMap.put("AP002", new AppointmentDTO("AP002", "2023-10-02", "11:00", patientMap.get("P002"), doctorMap.get("D002"), 700, "Scheduled"));
    }
    
}