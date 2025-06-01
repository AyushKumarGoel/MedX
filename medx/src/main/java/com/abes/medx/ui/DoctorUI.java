package com.abes.medx.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.UserException;
import com.abes.medx.service.AppointmentService;
import com.abes.medx.service.UserService;
import com.abes.medx.util.ValidationUtil;

/**
 * Handles all UI interactions for a logged-in Doctor.
 */
public class DoctorUI {
    private final Scanner scanner;
    private final UserService userService;
    private final AppointmentService appointmentService;

    public DoctorUI(Scanner scanner, UserService userService, AppointmentService appointmentService) {
        this.scanner = scanner;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    /**
     * Handles doctor login and shows doctor menu upon successful authentication.
     */
    public void handleDoctor() {
        try {
            System.out.print("Doctor Email: ");
            String email = scanner.nextLine();
            if (email.trim().isEmpty()) {
                throw new UserException("Email cannot be empty.");
            }

            System.out.print("Password: ");
            String password = scanner.nextLine();
            if (password.trim().isEmpty()) {
                throw new UserException("Password cannot be empty.");
            }

            DoctorDTO doctor = userService.doctorLogin(email, password);
            doctorMenu(doctor); // proceed to the doctor menu
        } catch (UserException e) {
            System.out.println("Doctor login failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during doctor login: " + e.getMessage());
        }
    }

    /**
     * Displays the doctor's main menu with available operations.
     */
    private void doctorMenu(DoctorDTO doctor) {
        while (true) {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. View My Appointments");
            System.out.println("2. Complete An Appointment");
            System.out.println("3. Update My Profile");
            System.out.println("4. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        viewAppointments(doctor);
                        break;
                    case "2":
                        completeAppointment();
                        break;
                    case "3":
                        updateDoctorProfile(doctor);
                        break;
                    case "4":
                        System.out.println("Doctor logged out.");
                        return;
                    default:
                        System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
                }
            } catch (AppointmentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    /**
     * Retrieves and displays appointments categorized as Pending/Approved or Completed.
     */
    private void viewAppointments(DoctorDTO doctor) throws AppointmentException {
        List<AppointmentDTO> doctorAppointments = appointmentService.getAppointmentsByDoctorId(doctor.getDoctorId());

        if (doctorAppointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            List<AppointmentDTO> pending = new ArrayList<>();
            List<AppointmentDTO> completed = new ArrayList<>();

            // Categorize appointments
            for (AppointmentDTO app : doctorAppointments) {
                String status = app.getStatus();
                if ("Approved".equalsIgnoreCase(status) || "Pending".equalsIgnoreCase(status)) {
                    pending.add(app);
                } else if ("Completed".equalsIgnoreCase(status)) {
                    completed.add(app);
                }
            }

            // Display Pending/Approved Appointments
            System.out.println("\n--- Pending/Approved Appointments ---");
            if (pending.isEmpty()) {
                System.out.println("No pending or approved appointments.");
            } else {
                for (AppointmentDTO app : pending) {
                    displayAppointment(app);
                }
            }

            // Display Completed Appointments
            System.out.println("\n--- Completed Appointments ---");
            if (completed.isEmpty()) {
                System.out.println("No completed appointments.");
            } else {
                for (AppointmentDTO app : completed) {
                    displayAppointment(app);
                }
            }
        }
    }

    /**
     * Marks an appointment as completed based on user input ID.
     */
    private void completeAppointment() throws AppointmentException {
        System.out.print("Enter appointment ID to complete: ");
        String id = scanner.nextLine();
        if (id.trim().isEmpty()) {
            throw new AppointmentException("Appointment ID cannot be empty.");
        }

        boolean success = appointmentService.completeAppointment(id);
        if (success) {
            System.out.println("Appointment marked as completed.");
        } else {
            System.out.println("Failed to complete appointment.");
        }
    }

    /**
     * Allows the doctor to update their profile with input validation.
     */
    private void updateDoctorProfile(DoctorDTO doctor) {
        try {
            System.out.println("Current details: " + doctor);
            System.out.print("New Name (press Enter to keep current): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                doctor.setName(ValidationUtil.validateName(name));
            }

            System.out.print("New Email (press Enter to keep current): ");
            String newEmail = scanner.nextLine();
            if (!newEmail.trim().isEmpty()) {
                doctor.setEmail(ValidationUtil.validateEmail(newEmail));
            }

            System.out.print("New Password (press Enter to keep current): ");
            String password = scanner.nextLine();
            if (!password.trim().isEmpty()) {
                doctor.setPassword(ValidationUtil.validatePassword(password));
            }

            System.out.print("New Phone Number (press Enter to keep current): ");
            String phoneNumber = scanner.nextLine();
            if (!phoneNumber.trim().isEmpty()) {
                doctor.setPhoneNumber(ValidationUtil.validatePhoneNumber(phoneNumber));
            }

            System.out.print("New Age (press Enter to keep current): ");
            String ageStr = scanner.nextLine();
            if (!ageStr.trim().isEmpty()) {
                doctor.setAge(ValidationUtil.validateAge(ageStr));
            }

            System.out.print("New Specialization (press Enter to keep current): ");
            String specialization = scanner.nextLine();
            if (!specialization.trim().isEmpty()) {
                doctor.setSpecialization(ValidationUtil.validateName(specialization));
            }

            System.out.print("New Years of Experience (press Enter to keep current): ");
            String yearsInput = scanner.nextLine();
            if (!yearsInput.trim().isEmpty()) {
                try {
                    int yearsOfExperience = Integer.parseInt(yearsInput);
                    if (yearsOfExperience < 0) {
                        throw new UserException("Years of experience cannot be negative.");
                    }
                    doctor.setYearsOfExperience(yearsOfExperience);
                } catch (NumberFormatException e) {
                    throw new UserException("Invalid input for years of experience. Please enter a number.");
                }
            }

            // Update the doctor profile via service
            if (userService.updateDoctorProfile(doctor)) {
                System.out.println("Profile updated successfully.");
            } else {
                throw new UserException("Failed to update profile.");
            }
        } catch (UserException e) {
            System.out.println("Error updating profile: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during profile update: " + e.getMessage());
        }
    }

    /**
     * Displays a single appointment in a formatted way.
     */
    private void displayAppointment(AppointmentDTO app) {
        System.out.println("========================================");
        System.out.println("Appointment ID : " + app.getAppointmentId());
        System.out.println("Date           : " + app.getAppointmentDate());
        System.out.println("Time           : " + app.getAppointmentTime());
        System.out.println("Patient Name   : " + app.getPatient().getName());
        System.out.println("Status         : " + app.getStatus());
        System.out.println("========================================");
    }
}
// End of DoctorUI.java
// This class handles all user interactions for a logged-in doctor, including viewing and completing appointments, and updating their profile.