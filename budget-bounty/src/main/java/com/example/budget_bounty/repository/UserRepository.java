package com.example.budget_bounty.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.budget_bounty.util.DatabaseConnection;

public class UserRepository {

    public void getAllUsers() {
        String query = "SELECT * FROM users";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

        	boolean hasResults = false;
        	while (resultSet.next()) {
                hasResults = true;
                // Retrieve data from resultSet and process it
                System.out.println("Username: " + resultSet.getString("username")); // Adjust column name if needed
                System.out.println("Email: " + resultSet.getString("email")); // Adjust column name if needed
            }

            if (!hasResults) {
                System.out.println("No users found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

