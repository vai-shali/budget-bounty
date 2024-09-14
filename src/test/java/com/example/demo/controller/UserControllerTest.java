package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Unit tests for UserController class.
 * 
 * @author Inas Kiren
 * @since 12th September 2024
 */
public class UserControllerTest {

    @InjectMocks
    // Inject the UserController into this test
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    // Initialize mocks before each test
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);

        ResponseEntity<String> response = userController.createUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());  // Verify the status code
        assertEquals("User created successfully", response.getBody());  // Verify the response body

        // Verify that saveUser method was called exactly once
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1));

        when(userService.getAllUsers()).thenReturn(users);  // Mock the service method

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());  // Verify the status code
        assertEquals(users, response.getBody());  // Verify the response body
    }

    @Test
    public void testGetUser() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);

        when(userService.getUserById(1)).thenReturn(user);  // Mock the service method

        ResponseEntity<User> response = userController.getUser(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());  // Verify the status code
        assertEquals(user, response.getBody());  // Verify the response body
    }

    @Test
    public void testUpdateUser() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);

        ResponseEntity<String> response = userController.updateUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());  // Verify the status code
        assertEquals("User updated successfully", response.getBody());  // Verify the response body

        // Verify that updateUser method was called exactly once
        verify(userService, times(1)).updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        ResponseEntity<String> response = userController.deleteUser(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());  // Verify the status code
        assertEquals("User deleted successfully", response.getBody());  // Verify the response body

        // Verify that deleteUser method was called exactly once
        verify(userService, times(1)).deleteUser(1);
    }

    @Test
    public void testExistsByEmail() {
        when(userService.existsByEmail("test@example.com")).thenReturn(true);  // Mock the service method

        ResponseEntity<Boolean> response = userController.existsByEmail("test@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());  // Verify the status code
        assertTrue(response.getBody());  // Verify the response body
    }

    @Test
    public void testExistsByUsername() {
        when(userService.existsByUsername("testuser")).thenReturn(true);  // Mock the service method

        ResponseEntity<Boolean> response = userController.existsByUsername("testuser");

        assertEquals(HttpStatus.OK, response.getStatusCode());  // Verify the status code
        assertTrue(response.getBody());  // Verify the response body
    }

    @Test
    public void testFindByEmailAndPassword_Success() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);
        when(userService.findByEmailAndPassword("test@example.com", "password")).thenReturn(user);  // Mock the service method

        ResponseEntity<User> response = userController.findByEmailAndPassword("test@example.com", "password");

        assertEquals(HttpStatus.OK, response.getStatusCode());  // Verify the status code
        assertEquals(user, response.getBody());  // Verify the response body
    }

    @Test
    public void testFindByEmailAndPassword_Failure() {
        when(userService.findByEmailAndPassword("test@example.com", "wrongpassword")).thenReturn(null);  // Mock the service method

        ResponseEntity<User> response = userController.findByEmailAndPassword("test@example.com", "wrongpassword");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());  // Verify the status code
    }

    @Test
    public void testFindByUsernameAndPassword_Success() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);
        //Mock the service
        when(userService.findByUsernameAndPassword("testuser", "password")).thenReturn(user);

        ResponseEntity<User> response = userController.findByUsernameAndPassword("testuser", "password");
        // Verify the status code
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verify the response body
        assertEquals(user, response.getBody());
    }

    @Test
    public void testFindByUsernameAndPassword_Failure() {
    	//Mock the service method
        when(userService.findByUsernameAndPassword("testuser", "wrongpassword")).thenReturn(null);

        ResponseEntity<User> response = userController.findByUsernameAndPassword("testuser", "wrongpassword");
        // Verify the status code
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode()); 
    }
}
