package hiber.hiber.service;

import java.util.List;

import hiber.hiber.model.Bank;
import hiber.hiber.repository.BankRepository;

/**
 * Service class for managing bank accounts.
 * Provides methods to perform CRUD operations on bank accounts.
 */
public class BankService {

    private BankRepository bankRepository;

    /**
     * Constructor to initialize the BankRepository.
     * Creates a new instance of BankRepository.
     */
    public BankService() {
        this.bankRepository = new BankRepository();
    }

    /**
     * Retrieves a list of all bank accounts.
     *
     * @return a list of all Bank objects
     */
    public List<Bank> getAllBanks() {
        return bankRepository.getAllBanks();
    }

    /**
     * Retrieves a bank account by its ID.
     *
     * @param bankId the ID of the bank account
     * @return the Bank object with the specified ID
     * @throws IllegalArgumentException if no bank account with the specified ID is found
     */
    public Bank getBankById(int bankId) {
        Bank bank = bankRepository.getBankById(bankId);
        if (bank == null) {
            throw new IllegalArgumentException("Bank with ID " + bankId + " not found.");
        }
        return bank;
    }

    /**
     * Adds a new bank account.
     *
     * @param bank the Bank object to be added
     * @throws IllegalArgumentException if both account number and UPI ID are null
     */
    public void addBank(Bank bank) {
        // Perform any necessary validation or business logic here
        if (bank.getAccountNumber() == null && bank.getUpiId() == null) {
            throw new IllegalArgumentException("Either account number or UPI ID must be provided.");
        }

        bankRepository.addBank(bank);
    }

    /**
     * Updates an existing bank account.
     *
     * @param bank the Bank object with updated information
     * @throws IllegalArgumentException if the bank account with the specified ID is not found
     */
    public void updateBank(Bank bank) {
        // Perform any necessary validation or business logic here
        Bank existingBank = bankRepository.getBankById(bank.getBankId());
        if (existingBank == null) {
            throw new IllegalArgumentException("Bank with ID " + bank.getBankId() + " not found.");
        }

        bankRepository.updateBank(bank);
    }

    /**
     * Deletes a bank account by its ID.
     *
     * @param bankId the ID of the bank account to be deleted
     * @throws IllegalArgumentException if the bank account with the specified ID is not found
     */
    public void deleteBank(int bankId) {
        // Perform any necessary validation or business logic here
        Bank existingBank = bankRepository.getBankById(bankId);
        if (existingBank == null) {
            throw new IllegalArgumentException("Bank with ID " + bankId + " not found.");
        }

        bankRepository.deleteBank(bankId);
    }
}
