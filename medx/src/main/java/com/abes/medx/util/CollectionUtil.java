package com.abes.medx.util;

import java.util.HashMap;
import java.util.Map;

public class CollectionUtil {
    public static final Map<Integer, PatientDTO> patientMap = new HashMap<>();

    static {
        // Static Patient Data
        patientMap.put(1, new PatientDTO("Raj", "Verma", "raj@gmail.com", "1234567890", "20", "P001"));
        patientMap.put(2, new PatientDTO("Yatharth", "Yadav", "yath@gmail.com", "1234599990", "21", "P002"));

    }
    
}
