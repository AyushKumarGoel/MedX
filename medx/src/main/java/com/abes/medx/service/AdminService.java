package com.abes.medx.service;

import com.abes.medx.dao.AdminDAO;
import com.abes.medx.dao.AdminDaoImpl;

public class AdminService {

    private final AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDaoImpl();
    }

    public boolean login(String adminId, String password) {
        return adminDAO.login(adminId, password);
    }

    public void addDoctor(String doctorId, String name, String specialization, int yearsOfExperience) {
        adminDAO.addDoctor(doctorId, name, specialization, yearsOfExperience);
    }

    public void removeDoctor(String doctorId) {
        adminDAO.removeDoctor(doctorId);
    }

    public String viewAllDoctors() {
        return adminDAO.viewAllDoctors();
    }

    public String viewAllPatients() {
        return adminDAO.viewAllPatients();
    }
}
