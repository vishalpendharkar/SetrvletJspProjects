package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/saveUser")
public class UserSave extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);

        Connection connection = null;
        PreparedStatement pst = null;

        try {
            // Get a connection from your custom connection class
            Conn con = new Conn();
            connection = con.getConnection();

            // Prepare the SQL query with placeholders
            String query = "INSERT INTO user (username, password) VALUES (?, ?)";
            pst = connection.prepareStatement(query);

            // Set the values for placeholders
            pst.setString(1, username);
            pst.setString(2, password);

            // Execute the query
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User saved successfully!");
                response.getWriter().println("User saved successfully!");
            } else {
                response.getWriter().println("Failed to save user.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while saving the user.");
        } finally {
            // Close resources
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

