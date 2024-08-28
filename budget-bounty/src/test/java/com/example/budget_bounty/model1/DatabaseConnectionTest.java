package com.example.budget_bounty.model1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.budget_bounty.util.DatabaseConnection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    private static Connection connection;

    @BeforeAll
    public static void setUp() {

        try {

            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            fail("Connection setup failed: " + e.getMessage());
        }
    }

    @Test
    public void testConnection() {
        assertDoesNotThrow(() -> {
            assertTrue(connection.isValid(2), "Connection is not valid");
        });
    }

    @AfterAll
    public static void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            fail("Failed to close connection: " + e.getMessage());
        }
    }
}


