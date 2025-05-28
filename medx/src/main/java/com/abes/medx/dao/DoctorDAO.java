package com.abes.medx.dao;

import com.abes.medx.dto.DoctorDTO;

import java.util.List;

public interface DoctorDAO {
    void addDoctor(DoctorDTO doctor);
    DoctorDTO getDoctorById(int id);
    List<DoctorDTO> getAllDoctors();
    void updateDoctor(DoctorDTO doctor);
    void deleteDoctor(int id);
    void showProfile(int id);
}
