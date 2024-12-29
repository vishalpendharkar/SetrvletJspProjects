package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

	Connection connection;
	

	public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Load the JDBC driver (optional for newer Java versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "2304"
            );        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to establish connection: " + e.getMessage());
            throw e; // Re-throwing SQLException for calling code to handle
        }
        return connection;
    }

}
