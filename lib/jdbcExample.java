package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcExample {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // step 1 register the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // step 2 establish the connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital", "root", "Tiger@12345");
        
        System.out.println("Connection created");
    }
}

