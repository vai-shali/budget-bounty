package hiber.hiber.service;

import java.sql.SQLException;
import java.util.List;

import hiber.hiber.model.User;
import hiber.hiber.repository.UserRepository;

public class UserService {
	private static UserRepository userRepository = new UserRepository();

    public UserService() {
        UserService.userRepository = new UserRepository();
    }
 // Save a new user
    public static void saveUser(User user) {
        try {
            userRepository.save(user);
            System.out.println("User saved successfully.");
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }
 // Find a user by ID
    public User getUserById(int userId) {
        try {
            return userRepository.findById(userId);
        } catch (SQLException e) {
            System.err.println("Error retrieving user by ID: " + e.getMessage());
            return null;
        }
    }
 // Get all users
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (SQLException e) {
            System.err.println("Error retrieving all users: " + e.getMessage());
            return null;
        }
    }

    // Update an existing user
    public void updateUser(User user) {
        try {
            userRepository.update(user);
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
    }

    // Delete a user by ID
    public void deleteUser(int userId) {
        try {
            userRepository.delete(userId);
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }

    // Check if a user exists by email
    public boolean existsByEmail(String email) {
        try {
            return userRepository.findByEmail(email) != null;
        } catch (SQLException e) {
            System.err.println("Error checking user existence by email: " + e.getMessage());
            return false;
        }
    }

    // Find a user by email and password
    public User findByEmailAndPassword(String email, String password) {
        try {
            return userRepository.findByEmailAndPassword(email, password);
        } catch (SQLException e) {
            System.err.println("Error finding user by email and password: " + e.getMessage());
            return null;
        }
    }
}
