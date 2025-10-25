package hms;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection conn;
    private Scanner sc = new Scanner(System.in);

    // constructor to receive connection
    public Patient(Connection conn) {
        this.conn = conn;
    }

    public void addPatient() throws SQLException {
        System.out.println("Enter Patient Name: ");
        String name = sc.next();

        System.out.println("Enter Patient Age: ");
        int age = sc.nextInt();

        System.out.println("Enter Patient Gender: ");
        String gender = sc.next();

        String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Patient details added successfully.");
            } else {
                System.out.println("❌ Failed to add patient details.");
            }
        }
    }

    public void viewPatients() throws SQLException {
        String query = "SELECT * FROM patients";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== Patient Details ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");

                System.out.println("Patient ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Gender: " + gender);
                System.out.println("---------------------------");
            }
        }
    }

    public boolean getPatientById(int id) throws SQLException {
        String query = "SELECT COUNT(1) FROM patients WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
