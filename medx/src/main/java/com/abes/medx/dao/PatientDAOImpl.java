package com.abes.medx.dao;

import java.util.ArrayList;
import java.util.List;

import com.abes.medx.dto.PatientDTO;
import com.abes.medx.util.CollectionUtil;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean register(PatientDTO patient) {
        if (CollectionUtil.patientMap.containsKey(patient.getPatientId())) return false;
        CollectionUtil.patientMap.put(patient.getPatientId(), patient);
        return true;
    }

    @Override
    public PatientDTO authenticate(String email, String password) {
        for (PatientDTO patient : CollectionUtil.patientMap.values()) {
            if (patient.getEmail().equalsIgnoreCase(email) && patient.getPassword().equals(password)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public boolean updateProfile(PatientDTO updatedPatient) {
        if (!CollectionUtil.patientMap.containsKey(updatedPatient.getPatientId())) return false;
        CollectionUtil.patientMap.put(updatedPatient.getPatientId(), updatedPatient);
        return true;
    }

    @Override
    public boolean delete(String email) {
        PatientDTO toDelete = getPatientByEmail(email);
        if (toDelete != null) {
            CollectionUtil.patientMap.remove(toDelete.getPatientId());
            return true;
        }
        return false;
    }

    @Override
    public PatientDTO getPatientByEmail(String email) {
        return CollectionUtil.patientMap.values().stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    
    @Override
    public List<PatientDTO> getAllPatients() {
        return new ArrayList<>(CollectionUtil.patientMap.values());
    }

}