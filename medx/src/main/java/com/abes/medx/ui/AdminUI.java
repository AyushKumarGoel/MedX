package com.abes.medx.ui;

import java.util.List;
import java.util.Scanner;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.service.UserService;

/**
 * AdminUI class handles all console-based admin operations
 * including login, managing doctors, patients, and other admins.
 */
public class AdminUI {

    private final Scanner scanner;
    private final UserService userService;

    public AdminUI(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    /**
     * Handles admin login and shows the admin menu upon successful authentication.
     */
    public void handleAdmin() {
        try {
            System.out.print("Admin Email: ");
            String email = scanner.nextLine();
            if (email.trim().isEmpty()) throw new UserException("Email cannot be empty.");

            System.out.print("Password: ");
            String password = scanner.nextLine();
            if (password.trim().isEmpty()) throw new UserException("Password cannot be empty.");

            AdminDTO admin = userService.adminLogin(email, password);
            adminMenu(admin);
        } catch (UserException e) {
            System.out.println("Admin login failed: " + e.getMessage());
        }
    }

    /**
     * Displays the admin menu and handles the selected operation.
     */
    private void adminMenu(AdminDTO admin) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Doctors");
            System.out.println("2. Register New Doctor");
            System.out.println("3. Update Doctor Profile");
            System.out.println("4. Delete Doctor");
            System.out.println("5. View All Patients");
            System.out.println("6. Register New Patient");
            System.out.println("7. Delete Patient");
            System.out.println("8. View All Admins");
            System.out.println("9. Register New Admin");
            System.out.println("10. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        List<DoctorDTO> doctors = userService.getAllDoctors();
                        doctors.forEach(System.out::println);
                        break;
                    case "2":
                        registerDoctor();
                        break;
                    case "3":
                        updateDoctor();
                        break;
                    case "4":
                        deleteDoctor();
                        break;
                    case "5":
                        List<PatientDTO> patients = userService.getAllPatients();
                        patients.forEach(System.out::println);
                        break;
                    case "6":
                        registerPatient();
                        break;
                    case "7":
                        deletePatient();
                        break;
                    case "8":
                        List<AdminDTO> admins = userService.getAllAdmins();
                        admins.forEach(System.out::println);
                        break;
                    case "9":
                        registerAdmin();
                        break;
                    case "10":
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (UserException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Handles registration of a new doctor.
     */
    private void registerDoctor() {
        try {
            System.out.print("Doctor ID: ");
            String doctorId = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Age: ");
            String age = scanner.nextLine();
            System.out.print("Specialization: ");
            String specialization = scanner.nextLine();
            System.out.print("Years of Experience: ");
            int yearsOfExperience = Integer.parseInt(scanner.nextLine());

            DoctorDTO doctor = new DoctorDTO(doctorId, name, email, password, phoneNumber, age, specialization, yearsOfExperience);
            userService.registerDoctor(doctor);
            System.out.println("Doctor registered successfully.");
        } catch (UserException e) {
            System.out.println("Registration failed: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for years of experience.");
        }
    }

    /**
     * Handles updating an existing doctor's profile.
     */
    private void updateDoctor() {
        try {
            List<DoctorDTO> doctors = userService.getAllDoctors();
            if (doctors.isEmpty()) {
                System.out.println("No doctors available to update.");
                return;
            }

            System.out.println("Available Doctors:");
            for (DoctorDTO doctor : doctors) {
                System.out.printf("ID: %s, Name: %s, Specialty: %s%n", doctor.getDoctorId(), doctor.getName(), doctor.getSpecialization());
            }

            System.out.print("Enter Doctor ID to update: ");
            String doctorId = scanner.nextLine().trim();

            DoctorDTO doctor = userService.getDoctorById(doctorId);
            if (doctor == null) {
                System.out.println("Doctor not found.");
                return;
            }

            // Allow selective updates
            System.out.print("New Name (press Enter to keep current): ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) doctor.setName(name);

            System.out.print("New Email (press Enter to keep current): ");
            String email = scanner.nextLine().trim();
            if (!email.isEmpty()) doctor.setEmail(email);

            System.out.print("New Password (press Enter to keep current): ");
            String password = scanner.nextLine().trim();
            if (!password.isEmpty()) doctor.setPassword(password);

            System.out.print("New Phone (press Enter to keep current): ");
            String phone = scanner.nextLine().trim();
            if (!phone.isEmpty()) doctor.setPhoneNumber(phone);

            System.out.print("New Specialization (press Enter to keep current): ");
            String specialization = scanner.nextLine().trim();
            if (!specialization.isEmpty()) doctor.setSpecialization(specialization);

            boolean updated = userService.updateDoctorProfile(doctor);
            System.out.println(updated ? "Doctor updated successfully." : "Update failed.");
        } catch (Exception e) {
            System.out.println("Error updating doctor: " + e.getMessage());
        }
    }

    /**
     * Handles deletion of a doctor by ID.
     */
    private void deleteDoctor() {
        try {
            List<DoctorDTO> doctors = userService.getAllDoctors();
            if (doctors.isEmpty()) {
                System.out.println("No doctors available to delete.");
                return;
            }

            System.out.println("Available Doctors:");
            for (DoctorDTO doctor : doctors) {
                System.out.printf("ID: %s, Name: %s, Specialty: %s%n", doctor.getDoctorId(), doctor.getName(), doctor.getSpecialization());
            }

            System.out.print("Enter Doctor ID to delete: ");
            String doctorId = scanner.nextLine().trim();
            boolean deleted = userService.deleteDoctor(doctorId);
            System.out.println(deleted ? "Doctor deleted successfully." : "Failed to delete doctor.");
        } catch (Exception e) {
            System.out.println("Error deleting doctor: " + e.getMessage());
        }
    }

    /**
     * Handles registration of a new patient.
     */
    private void registerPatient() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) throw new UserException("Name cannot be empty.");

            System.out.print("Email: ");
            String email = scanner.nextLine();
            if (email.trim().isEmpty()) throw new UserException("Email cannot be empty.");

            System.out.print("Password: ");
            String password = scanner.nextLine();
            if (password.trim().isEmpty()) throw new UserException("Password cannot be empty.");

            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Age: ");
            String age = scanner.nextLine();

            PatientDTO patient = new PatientDTO(userService.getNextPatientId(), name, email, password, phoneNumber, age);
            if (userService.registerPatient(patient)) {
                System.out.println("Patient registered successfully.");
            } else {
                System.out.println("Patient registration failed. ID or email may already exist.");
            }
        } catch (UserException e) {
            System.out.println("Error registering patient: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during patient registration: " + e.getMessage());
        }
    }

    /**
     * Handles deletion of a patient by ID.
     */
    private void deletePatient() {
        try {
            List<PatientDTO> patients = userService.getAllPatients();
            if (patients.isEmpty()) {
                System.out.println("No patients available to delete.");
                return;
            }

            System.out.println("Available Patients:");
            for (PatientDTO patient : patients) {
                System.out.printf("ID: %s, Name: %s, Age: %s%n", patient.getPatientId(), patient.getName(), patient.getAge());
            }

            System.out.print("Enter Patient ID to delete: ");
            String patientId = scanner.nextLine().trim();
            boolean deleted = userService.deletePatient(patientId);
            System.out.println(deleted ? "Patient deleted successfully." : "Failed to delete patient.");
        } catch (Exception e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        }
    }

    /**
     * Handles registration of a new admin.
     */
    private void registerAdmin() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) throw new UserException("Name cannot be empty.");

            System.out.print("Email: ");
            String email = scanner.nextLine();
            if (email.trim().isEmpty()) throw new UserException("Email cannot be empty.");

            System.out.print("Password: ");
            String password = scanner.nextLine();
            if (password.trim().isEmpty()) throw new UserException("Password cannot be empty.");

            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Age: ");
            String age = scanner.nextLine();

            AdminDTO admin = new AdminDTO(userService.getNextAdminId(), name, email, password, phoneNumber, age);
            if (userService.registerAdmin(admin)) {
                System.out.println("Admin registered successfully.");
            } else {
                System.out.println("Admin registration failed. ID or email may already exist.");
            }
        } catch (UserException e) {
            System.out.println("Error registering admin: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during admin registration: " + e.getMessage());
        }
    }
}
// End of AdminUI.java
// This class provides a console-based interface for admin users to manage doctors, patients, and other admins in the MedX application.