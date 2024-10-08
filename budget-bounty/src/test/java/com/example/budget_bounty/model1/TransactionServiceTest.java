package com.example.budget_bounty.model1;

import com.example.budget_bounty.repository.TransactionRepository;
import com.example.budget_bounty.service.TransactionService;

import model1.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionRepository transactionRepository;
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionService(transactionRepository);
    }

    // Test saveTransaction method
    @Test
    public void testSaveTransaction_Success() throws SQLException {
        Transaction transaction = new Transaction(); // Assume constructor sets default values

        transactionService.saveTransaction(transaction);

        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void testSaveTransaction_Failure() throws SQLException {
        Transaction transaction = new Transaction();
        doThrow(new SQLException("Database error")).when(transactionRepository).save(transaction);

        transactionService.saveTransaction(transaction);

        verify(transactionRepository, times(1)).save(transaction);
    }

    // Test getTransactionById method
    @Test
    public void testGetTransactionById_Success() throws SQLException {
        int transactionId = 1;
        Transaction mockTransaction = new Transaction();
        when(transactionRepository.findById(transactionId)).thenReturn(mockTransaction);

        Transaction result = transactionService.getTransactionById(transactionId);

        assertNotNull(result);
        assertEquals(mockTransaction, result);
    }

    @Test
    public void testGetTransactionById_Failure() throws SQLException {
        int transactionId = 1;
        when(transactionRepository.findById(transactionId)).thenThrow(new SQLException("Database error"));

        Transaction result = transactionService.getTransactionById(transactionId);

        assertNull(result);
    }

    // Test getTransactionsByUserId method
    @Test
    public void testGetTransactionsByUserId_Success() throws SQLException {
        int userId = 1;
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction());
        when(transactionRepository.findByUserId(userId)).thenReturn(mockTransactions);

        List<Transaction> result = transactionService.getTransactionsByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetTransactionsByUserId_Failure() throws SQLException {
        int userId = 1;
        when(transactionRepository.findByUserId(userId)).thenThrow(new SQLException("Database error"));

        List<Transaction> result = transactionService.getTransactionsByUserId(userId);

        assertNull(result);
    }

    // Test getAllTransactions method
    @Test
    public void testGetAllTransactions_Success() throws SQLException {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction());
        when(transactionRepository.findAll()).thenReturn(mockTransactions);

        List<Transaction> result = transactionService.getAllTransactions();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetAllTransactions_Failure() throws SQLException {
        when(transactionRepository.findAll()).thenThrow(new SQLException("Database error"));

        List<Transaction> result = transactionService.getAllTransactions();

        assertNull(result);
    }

    // Test updateTransaction method
    @Test
    public void testUpdateTransaction_Success() throws SQLException {
        Transaction transaction = new Transaction();

        transactionService.updateTransaction(transaction);

        verify(transactionRepository, times(1)).update(transaction);
    }

    @Test
    public void testUpdateTransaction_Failure() throws SQLException {
        Transaction transaction = new Transaction();
        doThrow(new SQLException("Database error")).when(transactionRepository).update(transaction);

        transactionService.updateTransaction(transaction);

        verify(transactionRepository, times(1)).update(transaction);
    }

    // Test deleteTransaction method
    @Test
    public void testDeleteTransaction_Success() throws SQLException {
        int transactionId = 1;

        transactionService.deleteTransaction(transactionId);

        verify(transactionRepository, times(1)).delete(transactionId);
    }

    @Test
    public void testDeleteTransaction_Failure() throws SQLException {
        int transactionId = 1;
        doThrow(new SQLException("Database error")).when(transactionRepository).delete(transactionId);

        transactionService.deleteTransaction(transactionId);

        verify(transactionRepository, times(1)).delete(transactionId);
    }
}