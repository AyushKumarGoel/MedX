package com.abes.medx.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.BookingException;
import com.abes.medx.exception.UserException;
import com.abes.medx.service.AppointmentServiceImpl;
import com.abes.medx.service.UserServiceImpl;
import com.abes.medx.util.ValidationUtil;

public class UI {

    private final Scanner scanner;
    private final UserServiceImpl userService;
    private final AppointmentServiceImpl appointmentService;

    public UI(Scanner scanner, UserServiceImpl userService, AppointmentServiceImpl appointmentService) {
        this.scanner = scanner;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    public void start() {
        while (true) {
            System.out.println("\n--- MedX System ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Doctor Login");
            System.out.println("3. Patient Login");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        handleAdmin();
                        break;
                    case "2":
                        handleDoctor();
                        break;
                    case "3":
                        handlePatient();
                        break;
                    case "4":
                        System.out.println("Exiting MedX. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private void handleAdmin() {
        try {
            System.out.print("Admin Email: ");
            String email = scanner.nextLine();
            email = ValidationUtil.validateEmail(email);
            System.out.print("Password: ");
            String password = scanner.nextLine();
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
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. View All Patients");
            System.out.println("6. Register New Patient");
            System.out.println("7. Delete Patient");
            System.out.println("8. View All Admins");
            System.out.println("9. Register New Admin");
            System.out.println("10. Update Admin");
            System.out.println("11. Delete Admin");
            System.out.println("12. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        List<DoctorDTO> doctors = userService.getAllDoctors();
                        if (doctors.isEmpty()) {
                            System.out.println("No doctors found.");
                        } else {
                            for (DoctorDTO doctor : doctors) {
                                System.out.println(doctor);
                            }
                        }
                        break;
                    case "2":
                        registerDoctor();
                        break;
                    case "3":
                        updateDoctor();
                        break;
                    case "4":
                        System.out.print("Enter doctor email to delete: ");
                        String docEmail = scanner.nextLine();
                        docEmail = ValidationUtil.validateEmail(docEmail);
                        if (userService.deleteDoctor(docEmail)) {
                            System.out.println("Doctor deleted successfully.");
                        } else {
                            System.out.println("Failed to delete doctor. Email not found.");
                        }
                        break;
                    case "5":
                        List<PatientDTO> patients = userService.getAllPatients();
                        if (patients.isEmpty()) {
                            System.out.println("No patients found.");
                        } else {
                            for (PatientDTO patient : patients) {
                                System.out.println(patient);
                            }
                        }
                        break;
                    case "6":
                        registerPatient();
                        break;
                    case "7":
                        System.out.print("Enter patient email to delete: ");
                        String patientEmail = scanner.nextLine();
                        patientEmail = ValidationUtil.validateEmail(patientEmail);
                        if (userService.deletePatient(patientEmail)) {
                            System.out.println("Patient deleted successfully.");
                        } else {
                            System.out.println("Failed to delete patient. Email not found.");
                        }
                        break;
                    case "8":
                        List<AdminDTO> admins = userService.getAllAdmins();
                        if (admins.isEmpty()) {
                            System.out.println("No admins found.");
                        } else {
                            for (AdminDTO a : admins) {
                                System.out.println(a);
                            }
                        }
                        break;
                    case "9":
                        registerAdmin();
                        break;
                    case "10":
                        updateAdmin();
                        break;
                    case "11":
                        System.out.print("Enter admin email to delete: ");
                        String adminEmail = scanner.nextLine();
                        adminEmail = ValidationUtil.validateEmail(adminEmail);
                        if (adminEmail.equals(admin.getEmail())) {
                            throw new UserException("Cannot delete your own account while logged in.");
                        }
                        if (userService.deleteAdmin(adminEmail)) {
                            System.out.println("Admin deleted successfully.");
                        } else {
                            System.out.println("Failed to delete admin. Email not found.");
                        }
                        break;
                    case "12":
                        System.out.println("Admin logged out.");
                        return;
                    default:
                        System.out.println("Invalid option. Please enter 1-12.");
                        break;
                }
            } catch (UserException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private void handleDoctor() {
        try {
            System.out.print("Doctor Email: ");
            String email = scanner.nextLine();
            email = ValidationUtil.validateEmail(email);
            System.out.print("Password: ");
            String password = scanner.nextLine();
            password = ValidationUtil.validatePassword(password);

            DoctorDTO doctor = userService.doctorLogin(email, password);
            doctorMenu(doctor);
        } catch (UserException e) {
            System.out.println("Doctor login failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during doctor login: " + e.getMessage());
        }
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
                        List<AppointmentDTO> doctorAppointments = appointmentService.getAppointmentsByDoctorId(doctor.getDoctorId());
                        if (doctorAppointments.isEmpty()) {
                            System.out.println("No appointments found.");
                        } else {
                            List<AppointmentDTO> pending = new ArrayList<>();
                            List<AppointmentDTO> completed = new ArrayList<>();

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
                                    System.out.println("========================================");
                                    System.out.println("Appointment ID : " + app.getAppointmentId());
                                    System.out.println("Date           : " + app.getAppointmentDate());
                                    System.out.println("Time           : " + app.getAppointmentTime());
                                    System.out.println("Patient Name   : " + app.getPatient().getName());
                                    System.out.println("Status         : " + app.getStatus());
                                    System.out.println("========================================");
                                }
                            }

                            // Display Completed Appointments
                            System.out.println("\n--- Completed Appointments ---");
                            if (completed.isEmpty()) {
                                System.out.println("No completed appointments.");
                            } else {
                                for (AppointmentDTO app : completed) {
                                    System.out.println("========================================");
                                    System.out.println("Appointment ID : " + app.getAppointmentId());
                                    System.out.println("Date           : " + app.getAppointmentDate());
                                    System.out.println("Time           : " + app.getAppointmentTime());
                                    System.out.println("Patient Name   : " + app.getPatient().getName());
                                    System.out.println("Status         : " + app.getStatus());
                                    System.out.println("========================================");
                                }
                            }
                        }
                        break;

                    case "2":
                        System.out.print("Enter appointment ID to complete: ");
                        String id = scanner.nextLine();
                        // Inline validation for appointment ID (must start with 'A' followed by digits)
                        if (!id.matches("^A\\d+$")) {
                            throw new AppointmentException("Appointment ID must start with 'A' followed by digits. Provided: " + id);
                        }
                        if (appointmentService.completeAppointment(id)) {
                            System.out.println("Appointment marked as completed.");
                        } else {
                            System.out.println("Failed to complete appointment.");
                        }
                        break;

                    case "3":
                        updateDoctorProfile(doctor);
                        break;

                    case "4":
                        System.out.println("Doctor logged out.");
                        return;

                    default:
                        System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
                        break;
                }
            } catch (AppointmentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private void handlePatient() {
        try {
            System.out.print("Patient Email: ");
            String email = scanner.nextLine();
            email = ValidationUtil.validateEmail(email);
            System.out.print("Password: ");
            String password = scanner.nextLine();
            password = ValidationUtil.validatePassword(password);

            PatientDTO patient = userService.patientLogin(email, password);
            patientMenu(patient);
        } catch (UserException e) {
            System.out.println("Patient login failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during patient login: " + e.getMessage());
        }
    }

    private void patientMenu(PatientDTO patient) {
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
                                System.out.println("Appointment: " + app.getAppointmentId() + " - " + app.getAppointmentDate() + " " + app.getAppointmentTime() + "\n" +
                                                   "Doctor: " + app.getDoctor().getName() + ", " + app.getDoctor().getSpecialization() + "\n" +
                                                   "Status: " + app.getStatus() + "\n");
                            }
                        }
                        break;
                    case "2":
                        bookAppointment(patient);
                        break;
                    case "3":
                        System.out.print("Enter appointment ID to cancel: ");
                        String id = scanner.nextLine();
                        // Inline validation for appointment ID (must start with 'A' followed by digits)
                        if (!id.matches("^A\\d+$")) {
                            throw new AppointmentException("Appointment ID must start with 'A' followed by digits. Provided: " + id);
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

    private void registerDoctor() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            name = ValidationUtil.validateName(name);
            System.out.print("Email: ");
            String email = scanner.nextLine();
            email = ValidationUtil.validateEmail(email);
            System.out.print("Password: ");
            String password = scanner.nextLine();
            password = ValidationUtil.validatePassword(password);
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            phoneNumber = ValidationUtil.validatePhoneNumber(phoneNumber);
            System.out.print("Age: ");
            String ageStr = scanner.nextLine();
            int age = Integer.parseInt(ValidationUtil.validateAge(ageStr));
            System.out.print("Specialization: ");
            String specialization = scanner.nextLine();
            specialization = ValidationUtil.validateName(specialization); // Reuse name validation for alphabetic chars
            System.out.print("Years of Experience: ");
            String yearsInput = scanner.nextLine();
            int yearsOfExperience;
            try {
                yearsOfExperience = Integer.parseInt(yearsInput);
                if (yearsOfExperience < 0) {
                    throw new UserException("Years of experience cannot be negative.");
                }
            } catch (NumberFormatException e) {
                throw new UserException("Invalid input for years of experience. Please enter a number.");
            }

            DoctorDTO doctor = new DoctorDTO(
                "D" + userService.getNextDoctorId(),
                name,
                email,
                password,
                phoneNumber,
                String.valueOf(age),
                specialization,
                yearsOfExperience
            );
            if (userService.registerDoctor(doctor)) {
                System.out.println("Doctor registered successfully.");
            } else {
                throw new UserException("Doctor registration failed. ID or email may already exist.");
            }
        } catch (UserException e) {
            System.out.println("Error registering doctor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during doctor registration: " + e.getMessage());
        }
    }

    private void updateDoctor() {
        try {
            System.out.print("Enter doctor email to update: ");
            String email = scanner.nextLine();
            email = ValidationUtil.validateEmail(email);
            DoctorDTO doctor = userService.getDoctorByEmail(email);
            if (doctor == null) {
                throw new UserException("Doctor with email " + email + " not found.");
            }

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

            if (userService.updateDoctorProfile(doctor)) {
                System.out.println("Doctor updated successfully.");
            } else {
                throw new UserException("Failed to update doctor.");
            }
        } catch (UserException e) {
            System.out.println("Error updating doctor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during doctor update: " + e.getMessage());
        }
    }

    private void registerPatient() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            name = ValidationUtil.validateName(name);
            System.out.print("Email: ");
            String email = scanner.nextLine();
            email = ValidationUtil.validateEmail(email);
            System.out.print("Password: ");
            String password = scanner.nextLine();
            password = ValidationUtil.validatePassword(password);
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            phoneNumber = ValidationUtil.validatePhoneNumber(phoneNumber);
            System.out.print("Age: ");
            String ageStr = scanner.nextLine();
            int age = Integer.parseInt(ValidationUtil.validateAge(ageStr));

            PatientDTO patient = new PatientDTO(userService.getNextPatientId(), name, email, password, phoneNumber, String.valueOf(age));
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

    private void registerAdmin() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            name = ValidationUtil.validateName(name);
            System.out.print("Email: ");
            String email = scanner.nextLine();
            email = ValidationUtil.validateEmail(email);
            System.out.print("Password: ");
            String password = scanner.nextLine();
            password = ValidationUtil.validatePassword(password);
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            phoneNumber = ValidationUtil.validatePhoneNumber(phoneNumber);
            System.out.print("Age: ");
            String ageStr = scanner.nextLine();
            int age = Integer.parseInt(ValidationUtil.validateAge(ageStr));

            String adminId = "A" + userService.getNextAdminId();
            AdminDTO admin = new AdminDTO(adminId, name, email, password, phoneNumber, String.valueOf(age));
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

    private void updateAdmin() {
        try {
            System.out.print("Enter admin email to update: ");
            String email = scanner.nextLine();
            email = ValidationUtil.validateEmail(email);
            AdminDTO admin = userService.getAdminByEmail(email);
            if (admin == null) {
                throw new UserException("Admin with email " + email + " not found.");
            }

            System.out.println("Current details: " + admin);
            System.out.print("New Name (press Enter to keep current): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                admin.setName(ValidationUtil.validateName(name));
            }
            System.out.print("New Email (press Enter to keep current): ");
            String newEmail = scanner.nextLine();
            if (!newEmail.trim().isEmpty()) {
                admin.setEmail(ValidationUtil.validateEmail(newEmail));
            }
            System.out.print("New Password (press Enter to keep current): ");
            String password = scanner.nextLine();
            if (!password.trim().isEmpty()) {
                admin.setPassword(ValidationUtil.validatePassword(password));
            }
            System.out.print("New Phone Number (press Enter to keep current): ");
            String phoneNumber = scanner.nextLine();
            if (!phoneNumber.trim().isEmpty()) {
                admin.setPhoneNumber(ValidationUtil.validatePhoneNumber(phoneNumber));
            }
            System.out.print("New Age (press Enter to keep current): ");
            String ageStr = scanner.nextLine();
            if (!ageStr.trim().isEmpty()) {
                admin.setAge(ValidationUtil.validateAge(ageStr));
            }

            if (userService.updateAdminProfile(admin)) {
                System.out.println("Admin updated successfully.");
            } else {
                throw new UserException("Failed to update admin.");
            }
        } catch (UserException e) {
            System.out.println("Error updating admin: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during admin update: " + e.getMessage());
        }
    }

    private void bookAppointment(PatientDTO patient) {
        try {
            System.out.print("Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            date = ValidationUtil.validateDate(date);

            System.out.print("Time (HH:MM): ");
            String time = scanner.nextLine();
            time = ValidationUtil.validateTime(time);

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
            if (!doctorId.matches("^D\\d+$")) {
                throw new AppointmentException("Doctor ID must start with 'D' followed by digits. Provided: " + doctorId);
            }

            System.out.print("Payment Amount: ");
            String paymentInput = scanner.nextLine();
            double toPay;
            try {
                toPay = Double.parseDouble(paymentInput);
                if (toPay < 0) {
                    throw new AppointmentException("Payment amount cannot be negative.");
                }
            } catch (NumberFormatException e) {
                throw new AppointmentException("Invalid payment amount. Please enter a valid number.");
            }

            AppointmentDTO appointment = appointmentService.createAppointment(
                appointmentService.getNextAppointmentId(), date, time, patient, doctorId, (int) toPay);

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

    private void updatePatientProfile(PatientDTO patient) {
        try {
            System.out.println("Current details: " + patient);
            System.out.print("New Name (press Enter to keep current): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                patient.setName(ValidationUtil.validateName(name));
            }
            System.out.print("New Email (press Enter to keep current): ");
            String newEmail = scanner.nextLine();
            if (!newEmail.trim().isEmpty()) {
                patient.setEmail(ValidationUtil.validateEmail(newEmail));
            }
            System.out.print("New Password (press Enter to keep current): ");
            String password = scanner.nextLine();
            if (!password.trim().isEmpty()) {
                patient.setPassword(ValidationUtil.validatePassword(password));
            }
            System.out.print("New Phone Number (press Enter to keep current): ");
            String phoneNumber = scanner.nextLine();
            if (!phoneNumber.trim().isEmpty()) {
                patient.setPhoneNumber(ValidationUtil.validatePhoneNumber(phoneNumber));
            }
            System.out.print("New Age (press Enter to keep current): ");
            String ageStr = scanner.nextLine();
            if (!ageStr.trim().isEmpty()) {
                patient.setAge(ValidationUtil.validateAge(ageStr));
            }

            if (userService.updatePatientProfile(patient)) {
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
}