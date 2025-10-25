package hms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookAppointment {
    private Connection connection;
    private Patient patient;
    private Doctor doctor;

    Scanner scanner = new Scanner(System.in);

    public BookAppointment(Connection connection, Patient patient, Doctor doctor) {
        this.connection = connection;
        this.patient = patient;
        this.doctor = doctor;
    }

    public void bookAppointment() throws SQLException {
        System.out.println("Enter patient ID: ");
        int patientId = scanner.nextInt();

        System.out.println("Enter doctor ID: ");
        int doctorId = scanner.nextInt();

        System.out.println("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();

        // validate patient
        if (!patient.getPatientById(patientId)) {
            System.out.println("❌ Invalid patient ID.");
            return;
        }

        // validate doctor
        if (!doctor.getDoctorById(doctorId)) {
            System.out.println("❌ Invalid doctor ID.");
            return;
        }

        // check if doctor already booked
        if (!checkAvailability(connection, doctorId, appointmentDate)) {
            System.out.println("❌ Doctor not available on that date.");
            return;
        }

        // insert appointment record
        String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);
            preparedStatement.setString(3, appointmentDate);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Appointment booked successfully!");
            } else {
                System.out.println("❌ Failed to book appointment.");
            }
        }
    }

    // method to check if doctor is available
    private boolean checkAvailability(Connection connection, int doctorId, String appointmentDate) throws SQLException {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, doctorId);
            ps.setString(2, appointmentDate);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count == 0; // true = available, false = booked
                }
            }
        }
        return true;
    }
}
