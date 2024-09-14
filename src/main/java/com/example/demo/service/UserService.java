package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private static UserRepository userRepository;

    /**
     * Default constructor to initialize the UserRepository.
     * Creates a new instance of UserRepository.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        UserService.userRepository = userRepository;
    }

    /**
     * Saves a new user.
     *
     * @param user the User object to be saved
     */
    public void saveUser(User user) {
	    userRepository.save(user);
	    System.out.println("User saved successfully.");
    }

    /**
     * Finds a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the User object with the specified ID, or null if not found
     */
    public User getUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all User objects, or null if an error occurs
     */
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Updates an existing user.
     *
     * @param user the User object with updated information
     */
    public void updateUser(User user) {
    	if(userRepository.existsById(user.getUserId())) {
            User existingUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

            existingUser.setName(user.getName());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            
            // Handle bank details
            if (user.getBankDetails() != null) {
                existingUser.setBankDetails(user.getBankDetails());
            } else {
                existingUser.setBankDetails(null); // explicitly set to null
            }

            userRepository.save(existingUser);
            System.out.println("User updated successfully.");
        } else {
            throw new IllegalArgumentException("User with ID " + user.getUserId() + " not found.");
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to be deleted
     */
    public void deleteUser(int userId) {
    	if(userRepository.existsById(userId)) {
    		userRepository.deleteById(userId);
    		System.out.println("User updated successfully.");
    	} else {
    		throw new IllegalArgumentException("User with ID " + userId + " not found.");
    	}
    }

    /**
     * Checks if a user exists by their email.
     *
     * @param email the email address to check
     * @return true if a user with the given email exists, false otherwise
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email)==1?true:false;
    }
    
    /**
     * Checks if a user exists by their email.
     *
     * @param email the email address to check
     * @return true if a user with the given email exists, false otherwise
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username)==1?true:false;
    }

    /**
     * Finds a user by their email and password.
     *
     * @param email the email address of the user
     * @param password the password of the user
     * @return the User object that matches the email and password, or null if not found
     */
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
    
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}

