package com.abes.medx.dao;

import com.abes.medx.dto.AdminDTO;

public interface AdminDAO {

    boolean login(String adminId, String password);
    
    void addDoctor(String doctorId, String name, String specialization,int yearsOfExperience);
    
    void removeDoctor(String doctorId);
    
    String viewAllDoctors();

    String viewAllPatients();
}
