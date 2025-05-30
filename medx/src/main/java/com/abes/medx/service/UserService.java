package com.abes.medx.service;

import java.util.List;

import com.abes.medx.dao.AdminDAO;
import com.abes.medx.dao.DoctorDAO;
import com.abes.medx.dao.PatientDAO;
import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;

public class UserService {

    private final AdminDAO adminDAO;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;

    public UserService(AdminDAO adminDAO, DoctorDAO doctorDAO, PatientDAO patientDAO) {
        this.adminDAO = adminDAO;
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    // Admin
    public AdminDTO adminLogin(String email, String password) {
        return adminDAO.authenticate(email, password);
    }

    public boolean registerAdmin(AdminDTO admin) {
        return adminDAO.register(admin);
    }

    public AdminDTO getAdminByEmail(String email) {
        return adminDAO.getAdminByEmail(email);
    }

    // Doctor
    public DoctorDTO doctorLogin(String email, String password) {
        return doctorDAO.authenticate(email, password);
    }

    public boolean registerDoctor(DoctorDTO doctor) {
        return doctorDAO.register(doctor);
    }

    public boolean deleteDoctor(String email) {
        return doctorDAO.delete(email);
    }

    public boolean updateDoctorProfile(DoctorDTO doctor) {
        return doctorDAO.updateProfile(doctor);
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }

    public DoctorDTO getDoctorById(String id) {
        return doctorDAO.getDoctorById(id);
    }

    // Patient
    public PatientDTO patientLogin(String email, String password) {
        return patientDAO.authenticate(email, password);
    }

    public boolean registerPatient(PatientDTO patient) {
        return patientDAO.register(patient);
    }

    public boolean updatePatientProfile(PatientDTO patient) {
        return patientDAO.updateProfile(patient);
    }

    public boolean deletePatient(String email) {
        return patientDAO.delete(email);
    }

    public List<PatientDTO> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    public PatientDTO getPatientByEmail(String email) {
        return patientDAO.getPatientByEmail(email);
    }

    public String getNextPatientId() {
        return patientDAO.getNextPatientId();
    }
}