package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User("John", "john123", "password", "1234567890", "john@example.com", 1);
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById_UserExists() {
        User user = new User(1, "John", "john123", "password", "1234567890", "john@example.com", 1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testGetUserById_UserDoesNotExist() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        User result = userService.getUserById(1);
        assertNull(result);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(
                new User(1, "John", "john123", "password", "1234567890", "john@example.com", 1),
                new User(2, "Jane", "jane123", "password", "0987654321", "jane@example.com", 2)
        );
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUser_UserExists() {
        User existingUser = new User(1, "John", "john123", "password", "1234567890", "john@example.com", 1);
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        User updatedUser = new User(1, "John Updated", "john123", "newpassword", "0987654321", "johnupdated@example.com", 1);
        userService.updateUser(updatedUser);

        verify(userRepository, times(1)).save(existingUser);
        assertEquals("John Updated", existingUser.getName());
        assertEquals("0987654321", existingUser.getPhoneNumber());
        assertEquals("johnupdated@example.com", existingUser.getEmail());
    }

    @Test
    void testUpdateUser_UserDoesNotExist() {
        User updatedUser = new User(1, "John Updated", "john123", "newpassword", "0987654321", "johnupdated@example.com", 1);
        when(userRepository.existsById(1)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.updateUser(updatedUser);
        });
        assertEquals("User with ID 1 not found.", exception.getMessage());
    }

    @Test
    void testDeleteUser_UserExists() {
        when(userRepository.existsById(1)).thenReturn(true);
        userService.deleteUser(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteUser_UserDoesNotExist() {
        when(userRepository.existsById(1)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser(1);
        });
        assertEquals("User with ID 1 not found.", exception.getMessage());
    }

    @Test
    void testExistsByEmail_UserExists() {
        when(userRepository.existsByEmail("john@example.com")).thenReturn(1);
        boolean exists = userService.existsByEmail("john@example.com");
        assertTrue(exists);
    }

    @Test
    void testExistsByEmail_UserDoesNotExist() {
        when(userRepository.existsByEmail("nonexistent@example.com")).thenReturn(0);
        boolean exists = userService.existsByEmail("nonexistent@example.com");
        assertFalse(exists);
    }

    @Test
    void testExistsByUsername_UserExists() {
        when(userRepository.existsByUsername("john123")).thenReturn(1);
        boolean exists = userService.existsByUsername("john123");
        assertTrue(exists);
    }

    @Test
    void testExistsByUsername_UserDoesNotExist() {
        when(userRepository.existsByUsername("nonexistentuser")).thenReturn(0);
        boolean exists = userService.existsByUsername("nonexistentuser");
        assertFalse(exists);
    }

    @Test
    void testFindByEmailAndPassword_ValidCredentials() {
        User user = new User(1, "John", "john123", "password", "1234567890", "john@example.com", 1);
        when(userRepository.findByEmailAndPassword("john@example.com", "password")).thenReturn(user);

        User result = userService.findByEmailAndPassword("john@example.com", "password");
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testFindByEmailAndPassword_InvalidCredentials() {
        when(userRepository.findByEmailAndPassword("john@example.com", "wrongpassword")).thenReturn(null);

        User result = userService.findByEmailAndPassword("john@example.com", "wrongpassword");
        assertNull(result);
    }

    @Test
    void testFindByUsernameAndPassword_ValidCredentials() {
        User user = new User(1, "John", "john123", "password", "1234567890", "john@example.com", 1);
        when(userRepository.findByUsernameAndPassword("john123", "password")).thenReturn(user);

        User result = userService.findByUsernameAndPassword("john123", "password");
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testFindByUsernameAndPassword_InvalidCredentials() {
        when(userRepository.findByUsernameAndPassword("john123", "wrongpassword")).thenReturn(null);

        User result = userService.findByUsernameAndPassword("john123", "wrongpassword");
        assertNull(result);
    }
}

