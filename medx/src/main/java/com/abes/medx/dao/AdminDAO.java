package com.abes.medx.dao;

public interface AdminDAO {

    boolean login(String adminId, String password);
    
    void addDoctor(String doctorId, String name, String specialization);
    
    void removeDoctor(String doctorId);
    
    String viewAllDoctors();

    String viewAllPatients();
}
