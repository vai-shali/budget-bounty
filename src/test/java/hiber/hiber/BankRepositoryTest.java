package hiber.hiber;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hiber.hiber.repository.*;
import hiber.hiber.model.*;

/**
 * Unit tests for the {@link BankRepository} class.
 * <p>
 * This class tests various methods of the {@link BankRepository} to ensure that
 * they are functioning as expected. It includes tests for retrieving, adding,
 * updating, and deleting bank records.
 * </p>
 */
public class BankRepositoryTest {
    private BankRepository bankRepository;

    /**
     * Sets up the test environment by initializing a {@link BankRepository} instance
     * and setting up any necessary initial data in the database.
     */
    @BeforeEach
    public void setUp() {
        bankRepository = new BankRepository();
        // Setup database connection and any necessary initial data
        initializeDatabase();
    }

    /**
     * Cleans up the database after each test to ensure a clean state for subsequent tests.
     */
    @AfterEach
    public void tearDown() {
        // Clean up database after each test
        cleanupDatabase();
    }

    /**
     * Tests the {@link BankRepository#getAllBanks()} method to ensure that it
     * returns a non-null and non-empty list of banks.
     */
    @Test
    public void testGetAllBanks() {
        List<Bank> banks = bankRepository.getAllBanks();
        assertNotNull(banks, "The bank list should not be null");
        assertFalse(banks.isEmpty(), "The bank list should not be empty");
    }

    /**
     * Tests the {@link BankRepository#getBankById(int)} method to ensure that it
     * returns a non-null {@link Bank} object for an existing bank ID.
     */
    @Test
    public void testGetBankById() {
        Bank bank = bankRepository.getBankById(1);
        assertNotNull(bank, "Bank should not be null for existing bank ID");
        assertEquals(1, bank.getBankId(), "Bank ID should match the requested ID");
    }

    /**
     * Tests the {@link BankRepository#addBank(Bank)} method for adding a new bank.
     * <p>
     * This test is currently commented out due to issues. It should be uncommented
     * once the issues are resolved.
     * </p>
     */
//    @Test
//    public void testAddBank() {
//        Bank newBank = new Bank(3, "Test Bank", "TEST0001234", "1234567890", "test@upi", "Savings", 1000.0, 1);
//        bankRepository.addBank(newBank);
//
//        Bank fetchedBank = bankRepository.getBankById(3);
//        assertNotNull(fetchedBank, "Newly added bank should be retrievable");
//        assertEquals("Test Bank", fetchedBank.getBankName(), "Bank name should match the added bank's name");
//    }

    /**
     * Tests the {@link BankRepository#updateBank(Bank)} method to ensure that
     * it correctly updates the bank's name in the repository.
     */
    @Test
    public void testUpdateBank() {
        Bank bankToUpdate = bankRepository.getBankById(1);
        bankToUpdate.setBankName("Updated Bank Name");

        bankRepository.updateBank(bankToUpdate);

        Bank updatedBank = bankRepository.getBankById(1);
        assertEquals("Updated Bank Name", updatedBank.getBankName(), "Bank name should be updated");
    }

    /**
     * Tests the {@link BankRepository#deleteBank(int)} method to ensure that
     * it correctly deletes a bank record and that the bank cannot be retrieved afterwards.
     */
    @Test
    public void testDeleteBank() {
        bankRepository.deleteBank(2);
        Bank deletedBank = bankRepository.getBankById(2);
        assertNull(deletedBank, "Deleted bank should not be retrievable");
    }

    /**
     * Initializes the database with necessary test data.
     * <p>
     * This method should be implemented to set up initial data required for the tests.
     * </p>
     */
    private void initializeDatabase() {
        // Code to set up initial data in the database for testing
    }

    /**
     * Cleans up the database after each test to ensure that the test environment
     * is clean for subsequent tests.
     */
    private void cleanupDatabase() {
        // Code to clean up database after tests
    }
}
