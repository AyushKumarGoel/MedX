package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.DoctorDTO;

public interface DoctorDAO {
    boolean register(DoctorDTO doctor);
    DoctorDTO authenticate(String email, String password);
    boolean updateProfile(DoctorDTO updatedDoctor);
    boolean delete(String email);
    DoctorDTO getDoctorByEmail(String email);
    DoctorDTO getDoctorById(String doctorId);
    List<DoctorDTO> getAllDoctors();
    String getNextDoctorId();
}