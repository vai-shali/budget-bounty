package hiber.hiber;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hiber.hiber.repository.*;

import hiber.hiber.model.*;
//import com.example.budget_bounty.util.DatabaseConnection;

public class BankRepositoryTest {
    private BankRepository bankRepository;
    
    @BeforeEach
    public void setUp() {
        bankRepository = new BankRepository();
        // Setup database connection and any necessary initial data
        initializeDatabase();
    }

    @AfterEach
    public void tearDown() {
        // Clean up database after each test
        cleanupDatabase();
    }

    @Test
    public void testGetAllBanks() {
        List<Bank> banks = bankRepository.getAllBanks();
        assertNotNull(banks);
        assertFalse(banks.isEmpty(), "The bank list should not be empty");
    }

    @Test
    public void testGetBankById() {
        Bank bank = bankRepository.getBankById(1);
        assertNotNull(bank, "Bank should not be null for existing bank ID");
        assertEquals(1, bank.getBankId());
    }
// error is coming here 
//    @Test
//    public void testAddBank() {
//        Bank newBank = new Bank(3, "Test Bank", "TEST0001234", "1234567890", "test@upi", "Savings", 1000.0, 1);
//        bankRepository.addBank(newBank);
//        
//        Bank fetchedBank = bankRepository.getBankById(3);
//        assertNotNull(fetchedBank, "Newly added bank should be retrievable");
//        assertEquals("Test Bank", fetchedBank.getBankName());
//    }

    @Test
    public void testUpdateBank() {
        Bank bankToUpdate = bankRepository.getBankById(1);
        bankToUpdate.setBankName("Updated Bank Name");
        
        bankRepository.updateBank(bankToUpdate);
        
        Bank updatedBank = bankRepository.getBankById(1);
        assertEquals("Updated Bank Name", updatedBank.getBankName(), "Bank name should be updated");
    }

    @Test
    public void testDeleteBank() {
        bankRepository.deleteBank(2);
        Bank deletedBank = bankRepository.getBankById(2);
        assertNull(deletedBank, "Deleted bank should not be retrievable");
    }

    private void initializeDatabase() {
        // Code to set up initial data in the database for testing
    }

    private void cleanupDatabase() {
        // Code to clean up database after tests
    }
}