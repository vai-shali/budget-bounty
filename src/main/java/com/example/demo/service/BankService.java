package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bank;
import com.example.demo.repository.BankRepository;

@Service
public class BankService {

    private final BankRepository bankRepository;

    /**
     * Constructor to initialize the BankRepository.
     * Creates a new instance of BankRepository.
     */
    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    /**
     * Retrieves a list of all bank accounts.
     *
     * @return a list of all Bank objects
     */
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    /**
     * Retrieves a bank account by its ID.
     *
     * @param bankId the ID of the bank account
     * @return the Bank object with the specified ID
     * @throws IllegalArgumentException if no bank account with the specified ID is found
     */
    public Bank getBankById(int bankId) {
        Optional<Bank> bank = bankRepository.findById(bankId);
        return bank.orElseThrow(() -> new IllegalArgumentException("Bank with ID " + bankId + " not found."));
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

        bankRepository.save(bank);
    }

    /**
     * Updates an existing bank account.
     *
     * @param bank the Bank object with updated information
     * @throws IllegalArgumentException if the bank account with the specified ID is not found
     */
    public void updateBank(Bank bank) {
        if(!bankRepository.existsById(bank.getBankId())) {
        	throw new IllegalArgumentException("Bank with ID " + bank.getBankId() + " not found.");
        }
        bankRepository.save(bank);
    }

    /**
     * Deletes a bank account by its ID.
     *
     * @param bankId the ID of the bank account to be deleted
     * @throws IllegalArgumentException if the bank account with the specified ID is not found
     */
    public void deleteBank(int bankId) {
    	if(!bankRepository.existsById(bankId)) {
        	throw new IllegalArgumentException("Bank with ID " + bankId + " not found.");
        }

        bankRepository.deleteById(bankId);
    }
}

