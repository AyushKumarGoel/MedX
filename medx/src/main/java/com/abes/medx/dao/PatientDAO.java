package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.PatientDTO;

public interface PatientDAO {
    boolean register(PatientDTO patient);
    PatientDTO authenticate(String email, String password);
    boolean updateProfile(PatientDTO updatedPatient);
    boolean delete(String email);
    PatientDTO getPatientByEmail(String email);
    List<PatientDTO> getAllPatients();

}
