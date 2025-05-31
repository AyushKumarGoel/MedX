package com.abes.medx.ui;

import java.util.List;
import java.util.Scanner;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.service.AppointmentServiceImpl;

public class PatientUI {
    private final AppointmentServiceImpl appointmentService;
    private final Scanner scanner;

    public PatientUI(AppointmentServiceImpl appointmentService, Scanner scanner) {
        this.appointmentService = appointmentService;
        this.scanner = scanner;
    }

    public void patientMenu(PatientDTO patient) {
        while (true) {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View My Appointments");
            System.out.println("2. Book Appointment");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Update My Profile");
            System.out.println("5. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.println("Appointment Details:");
                        List<AppointmentDTO> patientAppointments = appointmentService.getAppointmentsByPatientId(patient.getPatientId());
                        if (patientAppointments.isEmpty()) {
                            System.out.println("No appointments found.");
                        } else {
                            for (AppointmentDTO app : patientAppointments) {
                                System.out.println("Appointment: " + app.getAppointmentId()
                                        + " - " + app.getAppointmentDate() + " " + app.getAppointmentTime()
                                        + "\nDoctor: " + app.getDoctor().getName() + " (" + app.getDoctor().getSpecialization() + ")"
                                        + "\nStatus: " + app.getStatus() + "\n");
                            }
                        }
                        break;
                    case "2":
                        bookAppointment(patient);
                        break;
                    case "3":
                        System.out.print("Enter appointment ID to cancel: ");
                        String id = scanner.nextLine();
                        if (id.trim().isEmpty()) {
                            throw new AppointmentException("Appointment ID cannot be empty.");
                        }
                        if (appointmentService.cancelAppointment(id)) {
                            System.out.println("Appointment cancelled successfully.");
                        } else {
                            System.out.println("Failed to cancel appointment.");
                        }
                        break;
                    case "4":
                        updatePatientProfile(patient);
                        break;
                    case "5":
                        System.out.println("Patient logged out.");
                        return;
                    default:
                        System.out.println("Invalid option. Please enter 1, 2, 3, 4, or 5.");
                        break;
                }
            } catch (AppointmentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private void bookAppointment(PatientDTO patient) {
        // Placeholder: implement booking logic
        System.out.println("Book appointment functionality coming soon.");
    }

    private void updatePatientProfile(PatientDTO patient) {
        // Placeholder: implement profile update logic
        System.out.println("Update profile functionality coming soon.");
    }
}
