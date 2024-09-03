package hiber.hiber;

import hiber.hiber.repository.PaymentTransactionRepository;
import hiber.hiber.service.PaymentTransactionService;
import hiber.hiber.model.PaymentTransaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link PaymentTransactionService} class.
 * <p>
 * This class tests the functionality of the {@link PaymentTransactionService} methods
 * using mock data and Mockito for interaction with the {@link PaymentTransactionRepository}.
 * </p>
 * @author Amrisha
 * @since 2nd September, 2024
 */
public class TransactionServiceTest {

    private PaymentTransactionRepository transactionRepository;
    private PaymentTransactionService transactionService;

    /**
     * Set up method to initialize the {@link PaymentTransactionRepository} mock and
     * create a {@link PaymentTransactionService} instance before each test.
     */
    @BeforeEach
    public void setUp() {
        transactionRepository = mock(PaymentTransactionRepository.class);
        transactionService = new PaymentTransactionService(transactionRepository);
    }

    /**
     * Test for {@link PaymentTransactionService#saveTransaction(PaymentTransaction)} method.
     * <p>
     * Verifies that the method correctly saves a transaction and handles success cases.
     * </p>
     */
    @Test
    public void testSaveTransaction_Success() throws SQLException {
        PaymentTransaction transaction = new PaymentTransaction(); // Assume constructor sets default values

        transactionService.saveTransaction(transaction);

        verify(transactionRepository, times(1)).save(transaction);
    }

    /**
     * Test for {@link PaymentTransactionService#saveTransaction(PaymentTransaction)} method.
     * <p>
     * Verifies that the method handles failure cases when saving a transaction due to a database error.
     * </p>
     */
    @Test
    public void testSaveTransaction_Failure() throws SQLException {
        PaymentTransaction transaction = new PaymentTransaction();
        doThrow(new SQLException("Database error")).when(transactionRepository).save(transaction);

        transactionService.saveTransaction(transaction);

        verify(transactionRepository, times(1)).save(transaction);
    }

    /**
     * Test for {@link PaymentTransactionService#getTransactionById(int)} method.
     * <p>
     * Verifies that the method correctly retrieves a transaction by ID and handles success cases.
     * </p>
     */
    @Test
    public void testGetTransactionById_Success() throws SQLException {
        int transactionId = 1;
        PaymentTransaction mockTransaction = new PaymentTransaction();
        when(transactionRepository.findById(transactionId)).thenReturn(mockTransaction);

        PaymentTransaction result = transactionService.getTransactionById(transactionId);

        assertNotNull(result);
        assertEquals(mockTransaction, result);
    }

    /**
     * Test for {@link PaymentTransactionService#getTransactionById(int)} method.
     * <p>
     * Verifies that the method handles failure cases when retrieving a transaction by ID due to a database error.
     * </p>
     */
    @Test
    public void testGetTransactionById_Failure() throws SQLException {
        int transactionId = 1;
        when(transactionRepository.findById(transactionId)).thenThrow(new SQLException("Database error"));

        PaymentTransaction result = transactionService.getTransactionById(transactionId);

        assertNull(result);
    }

    /**
     * Test for {@link PaymentTransactionService#getTransactionsByUserId(int)} method.
     * <p>
     * Verifies that the method correctly retrieves transactions by user ID and handles success cases.
     * </p>
     */
    @Test
    public void testGetTransactionsByUserId_Success() throws SQLException {
        int userId = 1;
        List<PaymentTransaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new PaymentTransaction());
        when(transactionRepository.findByUserId(userId)).thenReturn(mockTransactions);

        List<PaymentTransaction> result = transactionService.getTransactionsByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test for {@link PaymentTransactionService#getTransactionsByUserId(int)} method.
     * <p>
     * Verifies that the method handles failure cases when retrieving transactions by user ID due to a database error.
     * </p>
     */
    @Test
    public void testGetTransactionsByUserId_Failure() throws SQLException {
        int userId = 1;
        when(transactionRepository.findByUserId(userId)).thenThrow(new SQLException("Database error"));

        List<PaymentTransaction> result = transactionService.getTransactionsByUserId(userId);

        assertNull(result);
    }

    /**
     * Test for {@link PaymentTransactionService#getAllTransactions()} method.
     * <p>
     * Verifies that the method correctly retrieves all transactions and handles success cases.
     * </p>
     */
    @Test
    public void testGetAllTransactions_Success() throws SQLException {
        List<PaymentTransaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new PaymentTransaction());
        when(transactionRepository.findAll()).thenReturn(mockTransactions);

        List<PaymentTransaction> result = transactionService.getAllTransactions();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test for {@link PaymentTransactionService#getAllTransactions()} method.
     * <p>
     * Verifies that the method handles failure cases when retrieving all transactions due to a database error.
     * </p>
     */
    @Test
    public void testGetAllTransactions_Failure() throws SQLException {
        when(transactionRepository.findAll()).thenThrow(new SQLException("Database error"));

        List<PaymentTransaction> result = transactionService.getAllTransactions();

        assertNull(result);
    }

    /**
     * Test for {@link PaymentTransactionService#updateTransaction(PaymentTransaction)} method.
     * <p>
     * Verifies that the method correctly updates a transaction and handles success cases.
     * </p>
     */
    @Test
    public void testUpdateTransaction_Success() throws SQLException {
        PaymentTransaction transaction = new PaymentTransaction();

        transactionService.updateTransaction(transaction);

        verify(transactionRepository, times(1)).update(transaction);
    }

    /**
     * Test for {@link PaymentTransactionService#updateTransaction(PaymentTransaction)} method.
     * <p>
     * Verifies that the method handles failure cases when updating a transaction due to a database error.
     * </p>
     */
    @Test
    public void testUpdateTransaction_Failure() throws SQLException {
        PaymentTransaction transaction = new PaymentTransaction();
        doThrow(new SQLException("Database error")).when(transactionRepository).update(transaction);

        transactionService.updateTransaction(transaction);

        verify(transactionRepository, times(1)).update(transaction);
    }

    /**
     * Test for {@link PaymentTransactionService#deleteTransaction(int)} method.
     * <p>
     * Verifies that the method correctly deletes a transaction and handles success cases.
     * </p>
     */
    @Test
    public void testDeleteTransaction_Success() throws SQLException {
        int transactionId = 1;

        transactionService.deleteTransaction(transactionId);

        verify(transactionRepository, times(1)).delete(transactionId);
    }

    /**
     * Test for {@link PaymentTransactionService#deleteTransaction(int)} method.
     * <p>
     * Verifies that the method handles failure cases when deleting a transaction due to a database error.
     * </p>
     */
    @Test
    public void testDeleteTransaction_Failure() throws SQLException {
        int transactionId = 1;
        doThrow(new SQLException("Database error")).when(transactionRepository).delete(transactionId);

        transactionService.deleteTransaction(transactionId);

        verify(transactionRepository, times(1)).delete(transactionId);
    }
}
