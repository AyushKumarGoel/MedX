package com.abes.medx.service;

import java.util.List;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;

public interface UserService {

    // Admin operations
    AdminDTO adminLogin(String email, String password) throws UserException;

    boolean registerAdmin(AdminDTO admin) throws UserException;

    boolean updateAdminProfile(AdminDTO admin) throws UserException;

    boolean deleteAdmin(String email) throws UserException;

    AdminDTO getAdminByEmail(String email) throws UserException;

    List<AdminDTO> getAllAdmins() throws UserException;

    String getNextAdminId() throws UserException;

    // Doctor operations
    DoctorDTO doctorLogin(String email, String password) throws UserException;

    boolean registerDoctor(DoctorDTO doctor) throws UserException;

    boolean updateDoctorProfile(DoctorDTO doctor) throws UserException;

    boolean deleteDoctor(String email) throws UserException;

    List<DoctorDTO> getAllDoctors() throws UserException;

    DoctorDTO getDoctorById(String id) throws UserException;

    DoctorDTO getDoctorByEmail(String email) throws UserException;

    String getNextDoctorId() throws UserException;

    // Patient operations
    PatientDTO patientLogin(String email, String password) throws UserException;

    boolean registerPatient(PatientDTO patient) throws UserException;

    boolean updatePatientProfile(PatientDTO patient) throws UserException;

    boolean deletePatient(String email) throws UserException;

    List<PatientDTO> getAllPatients() throws UserException;

    PatientDTO getPatientByEmail(String email) throws UserException;

    String getNextPatientId() throws UserException;
}
