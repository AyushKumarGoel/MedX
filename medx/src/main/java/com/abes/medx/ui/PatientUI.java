package com.abes.medx.ui;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.BookingException;
import com.abes.medx.exception.UserException;
import com.abes.medx.service.AppointmentService;
import com.abes.medx.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class PatientUI {
    private final AppointmentService appointmentService;
    private final UserService userService;
    private final Scanner scanner;

   public PatientUI(AppointmentService appointmentService, UserService userService, Scanner scanner) {
    this.appointmentService = appointmentService;
    this.userService = userService;
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
    try {
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new AppointmentException("Invalid date format. Use YYYY-MM-DD (e.g., 2025-12-31).");
        }

        System.out.print("Time (HH:MM): ");
        String time = scanner.nextLine();
        try {
            LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new AppointmentException("Invalid time format. Use HH:MM (e.g., 14:30).");
        }

        // Show all doctors
        List<DoctorDTO> doctors = userService.getAllDoctors();
        if (doctors == null || doctors.isEmpty()) {
            throw new AppointmentException("No doctors available.");
        }

        System.out.println("Available Doctors:");
        for (DoctorDTO doctor : doctors) {
            System.out.println("ID: " + doctor.getDoctorId() + ", Name: " + doctor.getName() + ", Specialty: " + doctor.getSpecialization());
        }

        System.out.print("Enter Doctor ID from the list above: ");
        String doctorId = scanner.nextLine();
        if (doctorId.trim().isEmpty()) {
            throw new AppointmentException("Doctor ID cannot be empty.");
        }

        // Removed payment input, assuming zero or default payment
        int toPay = 0;

        AppointmentDTO appointment = appointmentService.createAppointment(
            appointmentService.getNextAppointmentId(),
            date, time, patient, doctorId, toPay);

        if (appointment == null) {
            throw new AppointmentException("Failed to create appointment. Doctor may not exist or appointment ID may be taken.");
        }

        if (!appointmentService.bookAppointment(appointment)) {
            throw new BookingException("Failed to book appointment. There may be a scheduling conflict.");
        }

        System.out.println("Appointment booked successfully.");

    } catch (AppointmentException e) {
        System.out.println("Error: " + e.getMessage());
    } catch (BookingException e) {
        System.out.println("Booking error: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Unexpected error during appointment booking: " + e.getMessage());
    }
}

    private void updatePatientProfile(PatientDTO patient) {
    try {
        if (patient == null) {
            System.out.println("Invalid patient. Cannot update profile.");
            return;
        }

        System.out.println("Current Profile Details:");
        System.out.println(patient);

        System.out.print("New Name (press Enter to keep current): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            patient.setName(name);
        }

        System.out.print("New Email (press Enter to keep current): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) {
            patient.setEmail(email);
        }

        System.out.print("New Password (press Enter to keep current): ");
        String password = scanner.nextLine().trim();
        if (!password.isEmpty()) {
            patient.setPassword(password);
        }

        System.out.print("New Phone Number (press Enter to keep current): ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty()) {
            patient.setPhoneNumber(phone);
        }

        System.out.print("New Age (press Enter to keep current): ");
        String age = scanner.nextLine().trim();
        if (!age.isEmpty()) {
            patient.setAge(age);
        }

        boolean updated = userService.updatePatientProfile(patient);
        if (updated) {
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Failed to update profile. Please try again.");
        }

    } catch (UserException e) {
        System.out.println("Error updating profile: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Unexpected error during profile update: " + e.getMessage());
    }
}

}
