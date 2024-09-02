package com.example.budget_bounty.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.budget_bounty.util.DatabaseConnection;

import com.example.budget_bounty.model1.User;

public class UserRepository {
	public User findById(int userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRowToUser(resultSet);
            }
        }

        return null; // If no result found
    }
	public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                users.add(mapRowToUser(resultSet));
            }
        }

        return users;
    }
	public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRowToUser(resultSet);
            }
        }

        return null; // If no result found
    }

    // Method to find a user by email and password
    public User findByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRowToUser(resultSet);
            }
        }

        return null; // If no result found
    }
    public void update(User user) throws SQLException {
        String sql = "UPDATE Users SET name=?,username=?,password=?,phone_number=?,email=?,role=? where user_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(4, user.getPhoneNumber());
//            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setInt(6, user.getRole());
            preparedStatement.setInt(7, user.getUserId());

            preparedStatement.executeUpdate();
        }
    }
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO Users VALUES (?,?,?, ?, ?, ?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(2, user.getName());
//            preparedStatement.setString(6, user.getLastName());
            preparedStatement.setString(5, user.getPhoneNumber());
//            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setInt(7, user.getRole());

            preparedStatement.executeUpdate();
        }
    }
    public void delete(int userId) throws SQLException {
        String sql = "DELETE FROM Users WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        }
    }
	private User mapRowToUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("user_id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("phone_number"),
                resultSet.getString("email"),
                resultSet.getInt("role")
        );
    }
}

