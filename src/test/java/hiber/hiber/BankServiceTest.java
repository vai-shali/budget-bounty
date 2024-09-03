package hiber.hiber;

import hiber.hiber.repository.BankRepository;
import hiber.hiber.service.BankService;
import hiber.hiber.model.Bank;
import hiber.hiber.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link BankService} class.
 * <p>
 * This class tests various methods of the {@link BankService} to ensure that
 * they interact correctly with the {@link BankRepository} and handle data as expected.
 * It includes tests for retrieving, adding, updating, and deleting bank records.
 * </p>
 */
public class BankServiceTest {

    private BankRepository bankRepository;
    private BankService bankService;

    /**
     * Sets up the test environment by creating a mocked {@link BankRepository} instance
     * and initializing the {@link BankService} with it.
     * <p>
     * The mock repository is injected into the service using reflection.
     * </p>
     */
    @BeforeEach
    public void setUp() {
        bankRepository = mock(BankRepository.class);
        bankService = new BankService();

        // Use reflection to set the mocked repository into the BankService
        try {
            java.lang.reflect.Field field = BankService.class.getDeclaredField("bankRepository");
            field.setAccessible(true);
            field.set(bankService, bankRepository);
        } catch (Exception e) {
            fail("Failed to set bankRepository field");
        }
    }

    /**
     * Tests the {@link BankService#getAllBanks()} method to ensure that it correctly
     * retrieves a list of banks from the repository.
     */
    @Test
    public void testGetAllBanks() {
        List<Bank> mockBanks = new ArrayList<>();
        User userobj = new User();
        userobj.setUserId(1);
        mockBanks.add(new Bank(1, "Test Bank", "TEST1234", "123456789012", "test@upi", "Savings", 1000.00, userobj));
        when(bankRepository.getAllBanks()).thenReturn(mockBanks);

        List<Bank> result = bankService.getAllBanks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockBanks.get(0), result.get(0));
    }

    /**
     * Tests the {@link BankService#getBankById(int)} method to ensure that it
     * correctly retrieves a bank by its ID from the repository when the bank exists.
     */
    @Test
    public void testGetBankById_Success() {
        User userobj = new User();
        userobj.setUserId(1);
        Bank mockBank = new Bank(1, "Test Bank", "TEST1234", "123456789012", "test@upi", "Savings", 1000.00, userobj);
        when(bankRepository.getBankById(1)).thenReturn(mockBank);

        Bank result = bankService.getBankById(1);

        assertNotNull(result);
        assertEquals(mockBank, result);
    }

    /**
     * Tests the {@link BankService#getBankById(int)} method to ensure that it
     * throws an {@link IllegalArgumentException} when trying to retrieve a bank
     * by ID that does not exist.
     */
    @Test
    public void testGetBankById_NotFound() {
        when(bankRepository.getBankById(1)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankService.getBankById(1);
        });

        assertEquals("Bank with ID 1 not found.", exception.getMessage());
    }

    /**
     * Tests the {@link BankService#addBank(Bank)} method to ensure that it
     * correctly adds a new bank by calling the corresponding method in the repository.
     */
    @Test
    public void testAddBank_Success() {
        User userobj = new User();
        userobj.setUserId(1);
        Bank bank = new Bank(1, "Test Bank", "TEST1234", "123456789012", "test@upi", "Savings", 1000.00, userobj);

        bankService.addBank(bank);

        verify(bankRepository, times(1)).addBank(bank);
    }

    /**
     * Tests the {@link BankService#addBank(Bank)} method to ensure that it
     * throws an {@link IllegalArgumentException} if neither account number nor UPI ID is provided.
     */
    @Test
    public void testAddBank_NoAccountOrUpi() {
        Bank bank = new Bank(1, null, null, null, null, null, 0, null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankService.addBank(bank);
        });

        assertEquals("Either account number or UPI ID must be provided.", exception.getMessage());
    }

    /**
     * Tests the {@link BankService#updateBank(Bank)} method to ensure that it
     * correctly updates an existing bank by calling the corresponding method in the repository.
     */
    @Test
    public void testUpdateBank_Success() {
        User userobj = new User();
        userobj.setUserId(1);
        Bank existingBank = new Bank(1, "Test Bank", "TEST1234", "123456789012", "test@upi", "Savings", 1000.00, userobj);
        when(bankRepository.getBankById(1)).thenReturn(existingBank);

        bankService.updateBank(existingBank);

        verify(bankRepository, times(1)).updateBank(existingBank);
    }

    /**
     * Tests the {@link BankService#updateBank(Bank)} method to ensure that it
     * throws an {@link IllegalArgumentException} if trying to update a bank that does not exist.
     */
    @Test
    public void testUpdateBank_NotFound() {
        User userobj = new User();
        userobj.setUserId(1);
        Bank bankToUpdate = new Bank(1, "Test Bank", "TEST1234", "123456789012", "test@upi", "Savings", 1000.00, userobj);
        when(bankRepository.getBankById(1)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankService.updateBank(bankToUpdate);
        });

        assertEquals("Bank with ID 1 not found.", exception.getMessage());
    }

    /**
     * Tests the {@link BankService#deleteBank(int)} method to ensure that it
     * correctly deletes an existing bank by calling the corresponding method in the repository.
     */
    @Test
    public void testDeleteBank_Success() {
        User userobj = new User();
        userobj.setUserId(1);
        Bank existingBank = new Bank(1, "Test Bank", "TEST1234", "123456789012", "test@upi", "Savings", 1000.00, userobj);
        when(bankRepository.getBankById(1)).thenReturn(existingBank);

        bankService.deleteBank(1);

        verify(bankRepository, times(1)).deleteBank(1);
    }

    /**
     * Tests the {@link BankService#deleteBank(int)} method to ensure that it
     * throws an {@link IllegalArgumentException} if trying to delete a bank that does not exist.
     */
    @Test
    public void testDeleteBank_NotFound() {
        when(bankRepository.getBankById(1)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankService.deleteBank(1);
        });

        assertEquals("Bank with ID 1 not found.", exception.getMessage());
    }
}
