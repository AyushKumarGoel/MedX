package com.abes.medx.service;

import java.util.List;

import com.abes.medx.dao.AdminDAO;
import com.abes.medx.dao.DoctorDAO;
import com.abes.medx.dao.PatientDAO;
import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;

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
    public AdminDTO adminLogin(String email, String password) throws UserException {
        AdminDTO admin = adminDAO.authenticate(email, password);
        if (admin == null) throw new UserException("Invalid admin email or password.");
        return admin;
    }

    public boolean registerAdmin(AdminDTO admin) throws UserException {
        if (admin == null || admin.getEmail() == null || admin.getAdminId() == null) {
            throw new UserException("Invalid admin data provided.");
        }
        return adminDAO.register(admin);
    }

    public boolean updateAdminProfile(AdminDTO admin) throws UserException {
        if (admin == null || admin.getAdminId() == null || admin.getEmail() == null) {
            throw new UserException("Invalid admin data for update.");
        }
        return adminDAO.updateProfile(admin);
    }

    public boolean deleteAdmin(String email) throws UserException {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty.");
        }
        return adminDAO.delete(email);
    }

    public AdminDTO getAdminByEmail(String email) throws UserException {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty.");
        }
        return adminDAO.getAdminByEmail(email);
    }

    public List<AdminDTO> getAllAdmins() throws UserException {
        return adminDAO.getAllAdmins();
    }

    public String getNextAdminId() throws UserException {
        String nextId = adminDAO.getNextAdminId();
        if (nextId == null || nextId.trim().isEmpty()) {
            throw new UserException("Failed to generate next admin ID.");
        }
        return nextId;
    }

    // Doctor
    public DoctorDTO doctorLogin(String email, String password) throws UserException {
        DoctorDTO doctor = doctorDAO.authenticate(email, password);
        if (doctor == null) throw new UserException("Invalid doctor email or password.");
        return doctor;
    }

    public boolean registerDoctor(DoctorDTO doctor) throws UserException {
        if (doctor == null || doctor.getEmail() == null || doctor.getDoctorId() == null) {
            throw new UserException("Invalid doctor data provided.");
        }
        return doctorDAO.register(doctor);
    }

    public boolean deleteDoctor(String email) throws UserException {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty.");
        }
        return doctorDAO.delete(email);
    }

    public boolean updateDoctorProfile(DoctorDTO doctor) throws UserException {
        if (doctor == null || doctor.getDoctorId() == null) {
            throw new UserException("Invalid doctor data for update.");
        }
        return doctorDAO.updateProfile(doctor);
    }

    public List<DoctorDTO> getAllDoctors() throws UserException {
        return doctorDAO.getAllDoctors();
    }

    public DoctorDTO getDoctorById(String id) throws UserException {
        if (id == null || id.trim().isEmpty()) {
            throw new UserException("Doctor ID cannot be empty.");
        }
        return doctorDAO.getDoctorById(id);
    }

    public DoctorDTO getDoctorByEmail(String email) throws UserException {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty.");
        }
        return doctorDAO.getDoctorByEmail(email);
    }

    public String getNextDoctorId() throws UserException {
        String nextId = doctorDAO.getNextDoctorId();
        if (nextId == null || nextId.trim().isEmpty()) {
            throw new UserException("Failed to generate next doctor ID.");
        }
        return nextId;
    }

    // Patient
    public PatientDTO patientLogin(String email, String password) throws UserException {
        PatientDTO patient = patientDAO.authenticate(email, password);
        if (patient == null) throw new UserException("Invalid patient email or password.");
        return patient;
    }

    public boolean registerPatient(PatientDTO patient) throws UserException {
        if (patient == null || patient.getEmail() == null || patient.getPatientId() == null) {
            throw new UserException("Invalid patient data provided.");
        }
        return patientDAO.register(patient);
    }

    public boolean updatePatientProfile(PatientDTO patient) throws UserException {
        if (patient == null || patient.getPatientId() == null) {
            throw new UserException("Invalid patient data for update.");
        }
        return patientDAO.updateProfile(patient);
    }

    public boolean deletePatient(String email) throws UserException {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty.");
        }
        return patientDAO.delete(email);
    }

    public List<PatientDTO> getAllPatients() throws UserException {
        return patientDAO.getAllPatients();
    }

    public PatientDTO getPatientByEmail(String email) throws UserException {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty.");
        }
        return patientDAO.getPatientByEmail(email);
    }

    public String getNextPatientId() throws UserException {
        String nextId = patientDAO.getNextPatientId();
        if (nextId == null || nextId.trim().isEmpty()) {
            throw new UserException("Failed to generate next patient ID.");
        }
        return nextId;
    }
}
