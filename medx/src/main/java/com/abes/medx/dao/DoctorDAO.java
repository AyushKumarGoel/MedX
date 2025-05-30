package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.DoctorDTO;

public interface DoctorDAO {
    DoctorDTO authenticate(String email, String password);
    boolean register(DoctorDTO doctor);
    boolean delete(String email);
    boolean updateProfile(DoctorDTO doctor);
    List<DoctorDTO> getAllDoctors();
    DoctorDTO getDoctorById(String id);
    DoctorDTO getDoctorByEmail(String email);
    String getNextDoctorId();
}