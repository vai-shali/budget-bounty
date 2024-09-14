package com.example.demo.controller;

import com.example.demo.model.Bank;
import com.example.demo.model.User;
import com.example.demo.service.BankService;
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
 * Unit tests for the {@link BankController} class.
 * 
 * This class tests various methods of the {@link BankController} to ensure that
 * bank operations like retrieving, adding, updating, and deleting bank records
 * are functioning as expected.
 * 
 * @author Inas Kiren
 * @since 12th September 2024
 */

public class BankControllerTest {

    @InjectMocks
    private BankController bankController;

    @Mock
    private BankService bankService;


    /**
     * Initializes the necessary mocks before each test case.
     */
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    /**
     * Tests the {@link BankController#getAllBanks()} method to ensure that it
     * retrieves all banks successfully.
     */
    
    @Test
    public void testGetAllBanks() {
        List<Bank> banks = new ArrayList<>();
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);
        banks.add(new Bank(1, "Bank A", "IFSC001", "ACC001", "UPI001", "Savings", 1000.00, user));
        banks.add(new Bank(2, "Bank B", "IFSC002", "ACC002", "UPI002", "Checking", 1500.00, user));

        when(bankService.getAllBanks()).thenReturn(banks);

        ResponseEntity<List<Bank>> response = bankController.getAllBanks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(banks, response.getBody());
    }
    
    /**
     * Tests the {@link BankController#getBankById(int)} method to ensure that a
     * bank is retrieved successfully for a valid ID.
     */
    
    @Test
    public void testGetBankById_Success() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);
        Bank bank = new Bank(1, "Bank A", "IFSC001", "ACC001", "UPI001", "Savings", 1000.00, user);

        when(bankService.getBankById(1)).thenReturn(bank);

        ResponseEntity<Bank> response = bankController.getBankById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bank, response.getBody());
    }

    /**
     * Tests the {@link BankController#getBankById(int)} method when a bank is
     * not found for the given ID.
     */
    
    @Test
    public void testGetBankById_NotFound() {
        when(bankService.getBankById(99)).thenThrow(new IllegalArgumentException("Bank not found"));

        ResponseEntity<Bank> response = bankController.getBankById(99);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Tests the {@link BankController#addBank(Bank)} method to ensure that a
     * bank is added successfully.
     */
    
    @Test
    public void testAddBank_Success() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);
        Bank bank = new Bank(1, "Bank A", "IFSC001", "ACC001", "UPI001", "Savings", 1000.00, user);

        ResponseEntity<String> response = bankController.addBank(bank);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Bank added successfully", response.getBody());

        // Verify that addBank was called
        verify(bankService, times(1)).addBank(bank);
    }

    /**
     * Tests the {@link BankController#addBank(Bank)} method when invalid bank details
     * are provided, ensuring that it throws a bad request response.
     */
    
    @Test
    public void testAddBank_BadRequest() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);
        Bank bank = new Bank(1, "Bank A", "IFSC001", "ACC001", "UPI001", "Savings", 1000.00, user);
        doThrow(new IllegalArgumentException("Invalid bank details")).when(bankService).addBank(bank);

        ResponseEntity<String> response = bankController.addBank(bank);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid bank details", response.getBody());
    }

    /**
     * Tests the {@link BankController#updateBank(Bank)} method to ensure that a
     * bank is updated successfully.
     */
    
    @Test
    public void testUpdateBank_Success() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);
        Bank bank = new Bank(1, "Bank A", "IFSC001", "ACC001", "UPI001", "Savings", 1000.00, user);

        ResponseEntity<String> response = bankController.updateBank(bank);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bank updated successfully", response.getBody());

        // Verify that updateBank was called
        verify(bankService, times(1)).updateBank(bank);
    }

    /**
     * Tests the {@link BankController#updateBank(Bank)} method when the bank is
     * not found, ensuring that it returns a not found response.
     */
    
    @Test
    public void testUpdateBank_NotFound() {
        User user = new User(1, "Test User", "testuser", "password", "1234567890", "test@example.com", 1);
        Bank bank = new Bank(99, "Bank Z", "IFSC999", "ACC999", "UPI999", "Savings", 1000.00, user);
        doThrow(new IllegalArgumentException("Bank not found")).when(bankService).updateBank(bank);

        ResponseEntity<String> response = bankController.updateBank(bank);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Bank not found", response.getBody());
    }

    /**
     * Tests the {@link BankController#deleteBank(int)} method to ensure that a
     * bank is deleted successfully.
     */
    
    @Test
    public void testDeleteBank_Success() {
        ResponseEntity<String> response = bankController.deleteBank(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bank deleted successfully", response.getBody());

        // Verify that deleteBank was called
        verify(bankService, times(1)).deleteBank(1);
    }

    /**
     * Tests the {@link BankController#deleteBank(int)} method when the bank is
     * not found, ensuring that it returns a not found response.
     * 
     */
    
    @Test
    public void testDeleteBank_NotFound() {
        doThrow(new IllegalArgumentException("Bank not found")).when(bankService).deleteBank(99);

        ResponseEntity<String> response = bankController.deleteBank(99);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Bank not found", response.getBody());
    }
}