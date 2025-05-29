package com.abes.medx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;

public class CollectionUtil {

    public static final Map<String, PatientDTO> adminMap = new HashMap<>();
    public static final Map<String, PatientDTO> patientMap = new HashMap<>();
    public static final Map<String, DoctorDTO> doctorMap = new HashMap<>();
    public static final Map<String, AppointmentDTO> appointmentMap = new HashMap<>();
    public static final Map<String, List<String>> patientAppointments = new HashMap<>();
    public static final Map<String, List<String>> doctorAppointments = new HashMap<>();


    static {
        // Static Patient Data
        patientMap.put("P001", new PatientDTO("Raj Verma", "raj@gmail.com", "1234567890", "20", "P001"));
        patientMap.put("P002", new PatientDTO("Yatharth Yadav", "yath@gmail.com", "1234599990", "21", "P002"));

    }
    
}
