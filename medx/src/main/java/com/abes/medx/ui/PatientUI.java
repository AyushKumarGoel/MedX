package com.abes.medx.ui;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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

public class PatientUI {
    private final Scanner scanner;
    private final AppointmentService appointmentService;
    private final UserService userService;

    public PatientUI( Scanner scanner, AppointmentService appointmentService, UserService userService) {
        this.scanner = scanner;
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    public void handlePatient() {
        try {
            System.out.print("Patient Email: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) throw new UserException("Email cannot be empty.");

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            if (password.isEmpty()) throw new UserException("Password cannot be empty.");

            PatientDTO patient = userService.patientLogin(email, password);
            patientMenu(patient);
        } catch (UserException e) {
            System.out.println("Patient login failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during patient login: " + e.getMessage());
        }
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
                        viewAppointments(patient);
                        break;
                    case "2":
                        bookAppointment(patient);
                        break;
                    case "3":
                        cancelAppointment();
                        break;
                    case "4":
                        updatePatientProfile(patient);
                        break;
                    case "5":
                        System.out.println("Patient logged out.");
                        return;
                    default:
                        System.out.println("Invalid option. Please enter 1, 2, 3, 4, or 5.");
                }
            } catch (AppointmentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private void viewAppointments(PatientDTO patient) throws AppointmentException {
        System.out.println("Appointment Details:");
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByPatientId(patient.getPatientId());
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (AppointmentDTO app : appointments) {
                DoctorDTO doc = app.getDoctor();
                System.out.printf("Appointment: %s - %s %s%nDoctor: %s (%s)%nStatus: %s%n%n",
                        app.getAppointmentId(),
                        app.getAppointmentDate(),
                        app.getAppointmentTime(),
                        doc.getName(),
                        doc.getSpecialization(),
                        app.getStatus());
            }
        }
    }

    private void cancelAppointment() throws AppointmentException {
        System.out.print("Enter appointment ID to cancel: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) throw new AppointmentException("Appointment ID cannot be empty.");

        boolean cancelled = appointmentService.cancelAppointment(id);
        System.out.println(cancelled ? "Appointment cancelled successfully." : "Failed to cancel appointment.");
    }

    private void bookAppointment(PatientDTO patient) {
        try {
            System.out.print("Date (YYYY-MM-DD): ");
            String date = scanner.nextLine().trim();
            LocalDate parsedDate = LocalDate.parse(date); // May throw DateTimeParseException

            System.out.print("Time (HH:MM): ");
            String time = scanner.nextLine().trim();
            LocalTime parsedTime = LocalTime.parse(time); // May throw DateTimeParseException

            List<DoctorDTO> doctors = userService.getAllDoctors();
            if (doctors == null || doctors.isEmpty()) {
                throw new AppointmentException("No doctors available.");
            }

            System.out.println("Available Doctors:");
            for (DoctorDTO doctor : doctors) {
                System.out.printf("ID: %s, Name: %s, Specialty: %s%n",
                        doctor.getDoctorId(), doctor.getName(), doctor.getSpecialization());
            }

            System.out.print("Enter Doctor ID from the list above: ");
            String doctorId = scanner.nextLine().trim();
            if (doctorId.isEmpty()) throw new AppointmentException("Doctor ID cannot be empty.");

            int toPay = 0; // Hardcoded; assumed no payment handling needed

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

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date/time format. Use YYYY-MM-DD and HH:MM.");
        } catch (AppointmentException | BookingException e) {
            System.out.println("Error: " + e.getMessage());
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
            if (!name.isEmpty()) patient.setName(name);

            System.out.print("New Email (press Enter to keep current): ");
            String email = scanner.nextLine().trim();
            if (!email.isEmpty()) patient.setEmail(email);

            System.out.print("New Password (press Enter to keep current): ");
            String password = scanner.nextLine().trim();
            if (!password.isEmpty()) patient.setPassword(password);

            System.out.print("New Phone Number (press Enter to keep current): ");
            String phone = scanner.nextLine().trim();
            if (!phone.isEmpty()) patient.setPhoneNumber(phone);

            System.out.print("New Age (press Enter to keep current): ");
            String age = scanner.nextLine().trim();
            if (!age.isEmpty()) patient.setAge(age);

            boolean updated = userService.updatePatientProfile(patient);
            System.out.println(updated ? "Profile updated successfully." : "Failed to update profile.");

        } catch (UserException e) {
            System.out.println("Error updating profile: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during profile update: " + e.getMessage());
        }
    }
}