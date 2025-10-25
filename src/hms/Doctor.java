package hms;

import java.sql.*;
import java.util.Scanner;

public class Doctor {
    private Connection conn;
    private Scanner sc = new Scanner(System.in);

    // constructor to receive connection
    public Doctor(Connection conn) {
        this.conn = conn;
    }

    public void addDoctor() throws SQLException {
        System.out.println("Enter Doctor Name: ");
        String name = sc.next();

        System.out.println("Enter Department: ");
        String department = sc.next();

        String query = "INSERT INTO doctors(name, department) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, department);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Doctor details added successfully.");
            } else {
                System.out.println("❌ Failed to add Doctor details.");
            }
        }
    }

    public void viewDoctors() throws SQLException {
        String query = "SELECT * FROM doctors";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== Doctor Details ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");

                System.out.println("Doctor ID: " + id);
                System.out.println("Doctor Name: " + name);
                System.out.println("Department: " + department);
                System.out.println("---------------------------");
            }
        }
    }

    public boolean getDoctorById(int id) throws SQLException {
        String query = "SELECT COUNT(1) FROM doctors WHERE id = ?";
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
