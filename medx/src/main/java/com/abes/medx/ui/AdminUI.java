package com.abes.medx.ui;

import java.util.List;
import java.util.Scanner;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.service.UserService;
import com.abes.medx.util.ValidationUtil;

public class AdminUI {

    private final Scanner scanner;
    private final UserService userService;

    public AdminUI(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public void handleAdmin() {
        try {
            System.out.print("Admin Email: ");
            String email = scanner.nextLine().trim();
            email = ValidationUtil.validateEmail(email);

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            password = ValidationUtil.validatePassword(password);

            AdminDTO admin = userService.adminLogin(email, password);
            adminMenu(admin);
        } catch (UserException e) {
            System.out.println("Admin login failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during admin login: " + e.getMessage());
        }
    }

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
                        if (doctors.isEmpty()) {
                            System.out.println("No doctors found.");
                        } else {
                            doctors.forEach(System.out::println);
                        }
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
                        if (patients.isEmpty()) {
                            System.out.println("No patients found.");
                        } else {
                            patients.forEach(System.out::println);
                        }
                        break;
                    case "6":
                        registerPatient();
                        break;
                    case "7":
                        deletePatient();
                        break;
                    case "8":
                        List<AdminDTO> admins = userService.getAllAdmins();
                        if (admins.isEmpty()) {
                            System.out.println("No admins found.");
                        } else {
                            admins.forEach(System.out::println);
                        }
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
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private void registerDoctor() {
        try {
            
            

            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            name = ValidationUtil.validateName(name);

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            email = ValidationUtil.validateEmail(email);

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            password = ValidationUtil.validatePassword(password);

            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();
            phoneNumber = ValidationUtil.validatePhoneNumber(phoneNumber);

            System.out.print("Age: ");
            String age = scanner.nextLine().trim();
            age = ValidationUtil.validateAge(age);

            System.out.print("Specialization: ");
            String specialization = scanner.nextLine().trim();
            specialization = ValidationUtil.validateName(specialization);

            System.out.print("Years of Experience: ");
            String yearsInput = scanner.nextLine().trim();
            int yearsOfExperience;
            try {
                yearsOfExperience = Integer.parseInt(yearsInput);
                if (yearsOfExperience < 0) {
                    throw new UserException("Years of experience cannot be negative. Provided: " + yearsInput);
                }
            } catch (NumberFormatException e) {
                throw new UserException("Years of experience must be a valid number. Provided: " + yearsInput);
            }

            DoctorDTO doctor = new DoctorDTO(userService.getNextDoctorId(), name, email, password, phoneNumber, age, specialization, yearsOfExperience);
            userService.registerDoctor(doctor);
            System.out.println("Doctor registered successfully.");
        } catch (UserException e) {
            System.out.println("Registration failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during doctor registration: " + e.getMessage());
        }
    }

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
            if (doctorId.isEmpty()) {
                throw new UserException("Doctor ID cannot be empty.");
            }

            DoctorDTO doctor = userService.getDoctorById(doctorId);
            if (doctor == null) {
                throw new UserException("Doctor not found.");
            }

            System.out.print("New Name (press Enter to keep current): ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                name = ValidationUtil.validateName(name);
                doctor.setName(name);
            }

            System.out.print("New Email (press Enter to keep current): ");
            String email = scanner.nextLine().trim();
            if (!email.isEmpty()) {
                email = ValidationUtil.validateEmail(email);
                doctor.setEmail(email);
            }

            System.out.print("New Password (press Enter to keep current): ");
            String password = scanner.nextLine().trim();
            if (!password.isEmpty()) {
                password = ValidationUtil.validatePassword(password);
                doctor.setPassword(password);
            }

            System.out.print("New Phone (press Enter to keep current): ");
            String phone = scanner.nextLine().trim();
            if (!phone.isEmpty()) {
                phone = ValidationUtil.validatePhoneNumber(phone);
                doctor.setPhoneNumber(phone);
            }

            System.out.print("New Age (press Enter to keep current): ");
            String age = scanner.nextLine().trim();
            if (!age.isEmpty()) {
                age = ValidationUtil.validateAge(age);
                doctor.setAge(age);
            }

            System.out.print("New Specialization (press Enter to keep current): ");
            String specialization = scanner.nextLine().trim();
            if (!specialization.isEmpty()) {
                specialization = ValidationUtil.validateName(specialization);
                doctor.setSpecialization(specialization);
            }

            System.out.print("New Years of Experience (press Enter to keep current): ");
            String yearsInput = scanner.nextLine().trim();
            if (!yearsInput.isEmpty()) {
                try {
                    int yearsOfExperience = Integer.parseInt(yearsInput);
                    if (yearsOfExperience < 0) {
                        throw new UserException("Years of experience cannot be negative. Provided: " + yearsInput);
                    }
                    doctor.setYearsOfExperience(yearsOfExperience);
                } catch (NumberFormatException e) {
                    throw new UserException("Years of experience must be a valid number. Provided: " + yearsInput);
                }
            }

            boolean updated = userService.updateDoctorProfile(doctor);
            System.out.println(updated ? "Doctor updated successfully." : "Update composta.");
        } catch (UserException e) {
            System.out.println("Error updating doctor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during doctor update: " + e.getMessage());
        }
    }

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
            if (doctorId.isEmpty()) {
                throw new UserException("Doctor ID cannot be empty.");
            }

            boolean deleted = userService.deleteDoctor(doctorId);
            System.out.println(deleted ? "Doctor deleted successfully." : "Failed to delete doctor.");
        } catch (UserException e) {
            System.out.println("Error deleting doctor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during doctor deletion: " + e.getMessage());
        }
    }

    private void registerPatient() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            name = ValidationUtil.validateName(name);

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            email = ValidationUtil.validateEmail(email);

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            password = ValidationUtil.validatePassword(password);

            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();
            phoneNumber = ValidationUtil.validatePhoneNumber(phoneNumber);

            System.out.print("Age: ");
            String age = scanner.nextLine().trim();
            age = ValidationUtil.validateAge(age);

            PatientDTO patient = new PatientDTO(userService.getNextPatientId(), name, email, password, phoneNumber, age);
            if (userService.registerPatient(patient)) {
                System.out.println("Patient registered successfully.");
            } else {
                throw new UserException("Patient registration failed. ID or email may already exist.");
            }
        } catch (UserException e) {
            System.out.println("Error registering patient: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during patient registration: " + e.getMessage());
        }
    }

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
            if (patientId.isEmpty()) {
                throw new UserException("Patient ID cannot be empty.");
            }

            boolean deleted = userService.deletePatient(patientId);
            System.out.println(deleted ? "Patient deleted successfully." : "Failed to delete patient.");
        } catch (UserException e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during patient deletion: " + e.getMessage());
        }
    }

    private void registerAdmin() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            name = ValidationUtil.validateName(name);

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            email = ValidationUtil.validateEmail(email);

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            password = ValidationUtil.validatePassword(password);

            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();
            phoneNumber = ValidationUtil.validatePhoneNumber(phoneNumber);

            System.out.print("Age: ");
            String age = scanner.nextLine().trim();
            age = ValidationUtil.validateAge(age);

            AdminDTO admin = new AdminDTO(userService.getNextAdminId(), name, email, password, phoneNumber, age);
            if (userService.registerAdmin(admin)) {
                System.out.println("Admin registered successfully.");
            } else {
                throw new UserException("Admin registration failed. ID or email may already exist.");
            }
        } catch (UserException e) {
            System.out.println("Error registering admin: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during admin registration: " + e.getMessage());
        }
    }
}