package hiber.hiber.service;

import java.util.List;

import hiber.hiber.model.Bank;
import hiber.hiber.repository.BankRepository;

public class BankService {
    
    private BankRepository bankRepository;

    // Constructor to initialize the BankRepository
    public BankService() {
        this.bankRepository = new BankRepository();
    }

    // Method to get all bank accounts
    public List<Bank> getAllBanks() {
        return bankRepository.getAllBanks();
    }

    // Method to get a bank account by ID
    public Bank getBankById(int bankId) {
        Bank bank = bankRepository.getBankById(bankId);
        if (bank == null) {
            throw new IllegalArgumentException("Bank with ID " + bankId + " not found.");
        }
        return bank;
    }

    // Method to add a new bank account
    public void addBank(Bank bank) {
        // Perform any necessary validation or business logic here
        if (bank.getAccountNumber() == null && bank.getUpiId() == null) {
            throw new IllegalArgumentException("Either account number or UPI ID must be provided.");
        }
        
        bankRepository.addBank(bank);
    }

    // Method to update an existing bank account
    public void updateBank(Bank bank) {
        // Perform any necessary validation or business logic here
        Bank existingBank = bankRepository.getBankById(bank.getBankId());
        if (existingBank == null) {
            throw new IllegalArgumentException("Bank with ID " + bank.getBankId() + " not found.");
        }

        bankRepository.updateBank(bank);
    }

    // Method to delete a bank account by ID
    public void deleteBank(int bankId) {
        // Perform any necessary validation or business logic here
        Bank existingBank = bankRepository.getBankById(bankId);
        if (existingBank == null) {
            throw new IllegalArgumentException("Bank with ID " + bankId + " not found.");
        }

        bankRepository.deleteBank(bankId);
    }
}
