package hiber.hiber.service;

import java.sql.SQLException;
import java.util.List;

import hiber.hiber.model.User;
import hiber.hiber.repository.UserRepository;

/**
 * Service class for managing user accounts.
 * Provides methods to perform CRUD operations and user-related queries.
 */
public class UserService {
    private static UserRepository userRepository = new UserRepository();

    /**
     * Default constructor to initialize the UserRepository.
     * Creates a new instance of UserRepository.
     */
    public UserService() {
        UserService.userRepository = new UserRepository();
    }

    /**
     * Saves a new user.
     *
     * @param user the User object to be saved
     */
    public void saveUser(User user) {
        try {
            userRepository.save(user);
            System.out.println("User saved successfully.");
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    /**
     * Finds a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the User object with the specified ID, or null if not found
     */
    public User getUserById(int userId) {
        try {
            return userRepository.findById(userId);
        } catch (SQLException e) {
            System.err.println("Error retrieving user by ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all User objects, or null if an error occurs
     */
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (SQLException e) {
            System.err.println("Error retrieving all users: " + e.getMessage());
            return null;
        }
    }

    /**
     * Updates an existing user.
     *
     * @param user the User object with updated information
     */
    public void updateUser(User user) {
        try {
            userRepository.update(user);
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to be deleted
     */
    public void deleteUser(int userId) {
        try {
            userRepository.delete(userId);
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }

    /**
     * Checks if a user exists by their email.
     *
     * @param email the email address to check
     * @return true if a user with the given email exists, false otherwise
     */
    public boolean existsByEmail(String email) {
        try {
            return userRepository.findByEmail(email) != null;
        } catch (SQLException e) {
            System.err.println("Error checking user existence by email: " + e.getMessage());
            return false;
        }
    }

    /**
     * Finds a user by their email and password.
     *
     * @param email the email address of the user
     * @param password the password of the user
     * @return the User object that matches the email and password, or null if not found
     */
    public User findByEmailAndPassword(String email, String password) {
        try {
            return userRepository.findByEmailAndPassword(email, password);
        } catch (SQLException e) {
            System.err.println("Error finding user by email and password: " + e.getMessage());
            return null;
        }
    }
}
