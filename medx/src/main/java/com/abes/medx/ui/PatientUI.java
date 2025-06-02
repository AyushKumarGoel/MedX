package com.abes.medx.ui;

import java.util.List;
import java.util.Scanner;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.BookingException;
import com.abes.medx.exception.UserException;
import com.abes.medx.service.AppointmentService;
import com.abes.medx.service.UserService;
import com.abes.medx.util.ValidationUtil;

public class PatientUI {
    private final Scanner scanner;
    private final AppointmentService appointmentService;
    private final UserService userService;

    // Constructor to initialize services and scanner
    public PatientUI(Scanner scanner, AppointmentService appointmentService, UserService userService) {
        this.scanner = scanner;
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    // Handles patient login and routes to patient menu if successful
    public void handlePatient() {
        try {
            System.out.print("Patient Email: ");
            String email = scanner.nextLine().trim();
            email = ValidationUtil.validateEmail(email);

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            password = ValidationUtil.validatePassword(password);

            // Authenticate the patient
            PatientDTO patient = userService.patientLogin(email, password);
            patientMenu(patient);
        } catch (UserException e) {
            System.out.println("Patient login failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during patient login: " + e.getMessage());
        }
    }

    // Displays the patient menu and handles input
   public void patientMenu(PatientDTO patient) {
    while (true) {
        System.out.println("\n--- Patient Menu ---");
        System.out.println("1. View Active Appointments");
        System.out.println("2. View Completed Appointments");
        System.out.println("3. Book Appointment");
        System.out.println("4. Cancel Appointment");
        System.out.println("5. Update My Profile");
        System.out.println("6. Logout");
        System.out.print("Choose: ");
        String choice = scanner.nextLine();

        try {
            switch (choice) {
                case "1":
                    viewAppointmentsByStatus(patient, "Scheduled");
                    break;
                case "2":
                    viewAppointmentsByStatus(patient, "Completed");
                    viewAppointmentsByStatus(patient, "Cancelled");
                    break;
                case "3":
                    bookAppointment(patient);
                    break;
                case "4":
                    cancelAppointment();
                    break;
                case "5":
                    updatePatientProfile(patient);
                    break;
                case "6":
                    System.out.println("Patient logged out.");
                    return;
                default:
                    System.out.println("Invalid option. Please enter a number from 1 to 6.");
            }
        } catch (AppointmentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}

    // Displays all appointments for the logged-in patient
   private void viewAppointmentsByStatus(PatientDTO patient, String statusFilter) throws AppointmentException {
    List<AppointmentDTO> appointments = appointmentService.getAppointmentsByPatientId(patient.getPatientId());

    boolean found = false;
    System.out.println("\n--- " + statusFilter + " Appointments ---");

    for (AppointmentDTO app : appointments) {
        if (statusFilter.equalsIgnoreCase(app.getStatus())) {
            DoctorDTO doc = app.getDoctor();
            System.out.printf("Appointment: %s - %s %s%nDoctor: %s (%s)%nStatus: %s%n%n",
                    app.getAppointmentId(),
                    app.getAppointmentDate(),
                    app.getAppointmentTime(),
                    doc.getName(),
                    doc.getSpecialization(),
                    app.getStatus());
            found = true;
        }
    }

    if (!found) {
        System.out.println("No " + statusFilter.toLowerCase() + " appointments found.");
    }
}


    // Cancels an appointment by ID
    private void cancelAppointment() throws AppointmentException {
        System.out.print("Enter appointment ID to cancel: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) throw new AppointmentException("Appointment ID cannot be empty.");

        boolean cancelled = appointmentService.cancelAppointment(id);
        System.out.println(cancelled ? "Appointment cancelled successfully." : "Failed to cancel appointment.");
    }

    // Books a new appointment by selecting doctor, date, and time
    private void bookAppointment(PatientDTO patient) {
        try {
            // Take date and time input from user
            System.out.print("Date (YYYY-MM-DD): ");
            String date = scanner.nextLine().trim();
            date = ValidationUtil.validateDate(date);

            System.out.print("Time (HH:MM): ");
            String time = scanner.nextLine().trim();
            time = ValidationUtil.validateTime(time);

            // Display all available doctors
            List<DoctorDTO> doctors = userService.getAllDoctors();
            if (doctors == null || doctors.isEmpty()) {
                throw new AppointmentException("No doctors available.");
            }

            System.out.println("Available Doctors:");
            for (DoctorDTO doctor : doctors) {
                System.out.printf("ID: %s, Name: %s, Specialty: %s%n",
                        doctor.getDoctorId(), doctor.getName(), doctor.getSpecialization());
            }

            // Take doctor ID input
            System.out.print("Enter Doctor ID from the list above: ");
            String doctorId = scanner.nextLine().trim();
            if (doctorId.isEmpty()) throw new AppointmentException("Doctor ID cannot be empty.");

            // Payment logic is omitted; hardcoded to 0
            int toPay = 0;

            // Create and book the appointment
            AppointmentDTO appointment = appointmentService.createAppointment(
                    appointmentService.getNextAppointmentId(),
                    date,
                    time,
                    patient,
                    doctorId,
                    toPay
            );

            if (appointment == null) {
                throw new AppointmentException("Failed to create appointment. Check doctor or ID validity.");
            }

            if (!appointmentService.bookAppointment(appointment)) {
                throw new BookingException("Failed to book appointment. Possible schedule conflict.");
            }

            System.out.println("Appointment booked successfully.");

        } catch (AppointmentException | BookingException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during appointment booking: " + e.getMessage());
        }
    }

    // Allows patient to update their profile details
    private void updatePatientProfile(PatientDTO patient) {
        try {
            if (patient == null) {
                System.out.println("Invalid patient. Cannot update profile.");
                return;
            }

            // Display current profile info
            System.out.println("Current Profile Details:");
            System.out.println(patient);

            // Update each field only if user provides new input
            System.out.print("New Name (press Enter to keep current): ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                name = ValidationUtil.validateName(name);
                patient.setName(name);
            }

            System.out.print("New Email (press Enter to keep current): ");
            String email = scanner.nextLine().trim();
            if (!email.isEmpty()) {
                email = ValidationUtil.validateEmail(email);
                patient.setEmail(email);
            }

            System.out.print("New Password (press Enter to keep current): ");
            String password = scanner.nextLine().trim();
            if (!password.isEmpty()) {
                password = ValidationUtil.validatePassword(password);
                patient.setPassword(password);
            }

            System.out.print("New Phone Number (press Enter to keep current): ");
            String phone = scanner.nextLine().trim();
            if (!phone.isEmpty()) {
                phone = ValidationUtil.validatePhoneNumber(phone);
                patient.setPhoneNumber(phone);
            }

            System.out.print("New Age (press Enter to keep current): ");
            String age = scanner.nextLine().trim();
            if (!age.isEmpty()) {
                age = ValidationUtil.validateAge(age);
                patient.setAge(age);
            }

            // Update in backend
            boolean updated = userService.updatePatientProfile(patient);
            System.out.println(updated ? "Profile updated successfully." : "Failed to update profile.");

        } catch (UserException e) {
            System.out.println("Error updating profile: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during profile update: " + e.getMessage());
        }
    }
}