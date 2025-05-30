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
        try {
            AdminDTO admin = adminDAO.authenticate(email, password);
            if (admin == null) {
                throw new UserException("Invalid admin email or password.");
            }
            return admin;
        } catch (Exception e) {
            throw new UserException("Error during admin login: " + e.getMessage());
        }
    }

    public boolean registerAdmin(AdminDTO admin) throws UserException {
        try {
            if (admin == null || admin.getEmail() == null || admin.getAdminId() == null) {
                throw new UserException("Invalid admin data provided.");
            }
            return adminDAO.register(admin);
        } catch (Exception e) {
            throw new UserException("Error registering admin: " + e.getMessage());
        }
    }

    public boolean updateAdminProfile(AdminDTO admin) throws UserException {
        try {
            if (admin == null || admin.getAdminId() == null || admin.getEmail() == null) {
                throw new UserException("Invalid admin data for update.");
            }
            return adminDAO.updateProfile(admin);
        } catch (Exception e) {
            throw new UserException("Error updating admin profile: " + e.getMessage());
        }
    }

    public boolean deleteAdmin(String email) throws UserException {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new UserException("Email cannot be empty.");
            }
            return adminDAO.delete(email);
        } catch (Exception e) {
            throw new UserException("Error deleting admin: " + e.getMessage());
        }
    }

    public AdminDTO getAdminByEmail(String email) throws UserException {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new UserException("Email cannot be empty.");
            }
            return adminDAO.getAdminByEmail(email);
        } catch (Exception e) {
            throw new UserException("Error retrieving admin: " + e.getMessage());
        }
    }

    public List<AdminDTO> getAllAdmins() throws UserException {
        try {
            return adminDAO.getAllAdmins();
        } catch (Exception e) {
            throw new UserException("Error retrieving admins: " + e.getMessage());
        }
    }

    // Doctor
    public DoctorDTO doctorLogin(String email, String password) throws UserException {
        try {
            DoctorDTO doctor = doctorDAO.authenticate(email, password);
            if (doctor == null) {
                throw new UserException("Invalid doctor email or password.");
            }
            return doctor;
        } catch (Exception e) {
            throw new UserException("Error during doctor login: " + e.getMessage());
        }
    }

    public boolean registerDoctor(DoctorDTO doctor) throws UserException {
        try {
            if (doctor == null || doctor.getEmail() == null || doctor.getDoctorId() == null) {
                throw new UserException("Invalid doctor data provided.");
            }
            return doctorDAO.register(doctor);
        } catch (Exception e) {
            throw new UserException("Error registering doctor: " + e.getMessage());
        }
    }

    public boolean deleteDoctor(String email) throws UserException {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new UserException("Email cannot be empty.");
            }
            return doctorDAO.delete(email);
        } catch (Exception e) {
            throw new UserException("Error deleting doctor: " + e.getMessage());
        }
    }

    public boolean updateDoctorProfile(DoctorDTO doctor) throws UserException {
        try {
            if (doctor == null || doctor.getDoctorId() == null) {
                throw new UserException("Invalid doctor data for update.");
            }
            return doctorDAO.updateProfile(doctor);
        } catch (Exception e) {
            throw new UserException("Error updating doctor profile: " + e.getMessage());
        }
    }

    public List<DoctorDTO> getAllDoctors() throws UserException {
        try {
            return doctorDAO.getAllDoctors();
        } catch (Exception e) {
            throw new UserException("Error retrieving doctors: " + e.getMessage());
        }
    }

    public DoctorDTO getDoctorById(String id) throws UserException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new UserException("Doctor ID cannot be empty.");
            }
            return doctorDAO.getDoctorById(id);
        } catch (Exception e) {
            throw new UserException("Error retrieving doctor by ID: " + e.getMessage());
        }
    }

    public DoctorDTO getDoctorByEmail(String email) throws UserException {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new UserException("Email cannot be empty.");
            }
            return doctorDAO.getDoctorByEmail(email);
        } catch (Exception e) {
            throw new UserException("Error retrieving doctor by email: " + e.getMessage());
        }
    }

    // Patient
    public PatientDTO patientLogin(String email, String password) throws UserException {
        try {
            PatientDTO patient = patientDAO.authenticate(email, password);
            if (patient == null) {
                throw new UserException("Invalid patient email or password.");
            }
            return patient;
        } catch (Exception e) {
            throw new UserException("Error during patient login: " + e.getMessage());
        }
    }

    public boolean registerPatient(PatientDTO patient) throws UserException {
        try {
            if (patient == null || patient.getEmail() == null || patient.getPatientId() == null) {
                throw new UserException("Invalid patient data provided.");
            }
            return patientDAO.register(patient);
        } catch (Exception e) {
            throw new UserException("Error registering patient: " + e.getMessage());
        }
    }

    public boolean updatePatientProfile(PatientDTO patient) throws UserException {
        try {
            if (patient == null || patient.getPatientId() == null) {
                throw new UserException("Invalid patient data for update.");
            }
            return patientDAO.updateProfile(patient);
        } catch (Exception e) {
            throw new UserException("Error updating patient profile: " + e.getMessage());
        }
    }

    public boolean deletePatient(String email) throws UserException {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new UserException("Email cannot be empty.");
            }
            return patientDAO.delete(email);
        } catch (Exception e) {
            throw new UserException("Error deleting patient: " + e.getMessage());
        }
    }

    public List<PatientDTO> getAllPatients() throws UserException {
        try {
            return patientDAO.getAllPatients();
        } catch (Exception e) {
            throw new UserException("Error retrieving patients: " + e.getMessage());
        }
    }

    public PatientDTO getPatientByEmail(String email) throws UserException {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new UserException("Email cannot be empty.");
            }
            return patientDAO.getPatientByEmail(email);
        } catch (Exception e) {
            throw new UserException("Error retrieving patient: " + e.getMessage());
        }
    }

    public String getNextPatientId() throws UserException {
        try {
            String nextId = patientDAO.getNextPatientId();
            if (nextId == null || nextId.trim().isEmpty()) {
                throw new UserException("Failed to generate next patient ID.");
            }
            return nextId;
        } catch (Exception e) {
            throw new UserException("Error generating next patient ID: " + e.getMessage());
        }
    }

    public String getNextDoctorId() throws UserException {
        try {
            String nextId = doctorDAO.getNextDoctorId();
            if (nextId == null || nextId.trim().isEmpty()) {
                throw new UserException("Failed to generate next doctor ID.");
            }
            return nextId;
        } catch (Exception e) {
            throw new UserException("Error generating next doctor ID: " + e.getMessage());
        }
    }

    public String getNextAdminId() throws UserException {
        try {
            String nextId = adminDAO.getNextAdminId();
            if (nextId == null || nextId.trim().isEmpty()) {
                throw new UserException("Failed to generate next admin ID.");
            }
            return nextId;
        } catch (Exception e) {
            throw new UserException("Error generating next admin ID: " + e.getMessage());
        }
    }
}