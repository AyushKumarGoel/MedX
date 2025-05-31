package com.abes.medx.ui;

import java.util.List;
import java.util.Scanner;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.UserException;
import com.abes.medx.service.AppointmentService;
import com.abes.medx.service.UserService;
import com.abes.medx.util.CollectionUtil;

public class DoctorUI {
    private final Scanner scanner;
    private final UserService userService;
    private final AppointmentService appointmentService;

    public DoctorUI(Scanner scanner, UserService userService, AppointmentService appointmentService) {
        this.scanner = scanner;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

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
                doctorMenu(doctor);
            } catch (UserException e) {
                System.out.println("Doctor login failed: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error during doctor login: " + e.getMessage());
            }
        }

    private void viewAppointments(DoctorDTO doctor) throws AppointmentException {
        List<AppointmentDTO> doctorAppointments = appointmentService.getAppointmentsByDoctorId(doctor.getDoctorId());
        if (doctorAppointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }


        for (AppointmentDTO app : doctorAppointments) {
            if ("Scheduled".equalsIgnoreCase(app.getStatus())) {
                CollectionUtil.scheduled.add(app);
            } else if ("Completed".equalsIgnoreCase(app.getStatus())) {
                CollectionUtil.completed.add(app);
            }
        }

        System.out.println("\n--- Scheduled Appointments ---");
        if (CollectionUtil.scheduled.isEmpty()) {
            System.out.println("No scheduled appointments.");
        } else {
            for (AppointmentDTO app : CollectionUtil.scheduled) {
                displayAppointment(app);
            }
        }

        System.out.println("\n--- Completed Appointments ---");
        if (CollectionUtil.completed.isEmpty()) {
            System.out.println("No completed appointments.");
        } else {
            for (AppointmentDTO app : CollectionUtil.completed) {
                displayAppointment(app);
            }
        }
    }

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

    private void updateDoctorProfile(DoctorDTO doctor) {
        // Placeholder for profile update logic
        System.out.println("Update Profile feature not implemented yet.");
    }

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
