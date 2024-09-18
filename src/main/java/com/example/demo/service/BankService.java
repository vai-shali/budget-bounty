package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bank;
import com.example.demo.model.User;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.UserRepository;

@Service
public class BankService {

	@Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

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
    
    public Bank getBankByUserId(int userId) {
    	Optional<Bank> bank = bankRepository.findByUserUserId(userId);
        return bank.orElseThrow(() -> new IllegalArgumentException("Bank account nout found for user!"));
    }

    /**
     * Adds a new bank account.
     *
     * @param bank the Bank object to be added
     * @throws IllegalArgumentException if both account number and UPI ID are null
     */
    public void addBank(Bank bank, int userId) {
        
    	User existingUser = userRepository.findById(userId).orElse(null);
    	if(existingUser==null) {
    		throw new IllegalArgumentException("User does not exist!");
    	}
    	
        if (bank.getAccountNumber() == null && bank.getUpiId() == null) {
            throw new IllegalArgumentException("Either account number or UPI ID must be provided.");
        }
        bank.setUser(existingUser);
        bankRepository.save(bank);
    }

    /**
     * Updates an existing bank account.
     *
     * @param bank the Bank object with updated information
     * @throws IllegalArgumentException if the bank account with the specified ID is not found
     */
    public void updateBank(Bank bank, int bankId) {
        if(!bankRepository.existsById(bankId)) {
        	throw new IllegalArgumentException("Bank with ID " + bank.getBankId() + " not found.");
        }
        
        Bank existingBank = bankRepository.findById(bankId).orElseThrow(() -> 
        new IllegalArgumentException("Bank with ID " + bankId + " not found."));

	    // Update the existing bank entity with the new values
	    existingBank.setBankName(bank.getBankName());
	    existingBank.setIfsc(bank.getIfsc());
	    existingBank.setAccountNumber(bank.getAccountNumber());
	    existingBank.setUpiId(bank.getUpiId());
	    existingBank.setAccountType(bank.getAccountType());
	    existingBank.setBalance(bank.getBalance());
//	    existingBank.setUser(bank.getUser());
        
        bankRepository.save(existingBank);
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
    
    //FUNCTIONS FOR MAKE PAYMENT SERVICE
    public void updateBalance(int userId, double amount) {
    	Bank bank=bankRepository.findByUserUserId(userId).orElse(null);
    	if(bank==null) {
    		throw new IllegalArgumentException("User not found with user ID: "+userId);
    	}
    	bank.setBalance(amount);
    	bankRepository.save(bank);
    	
    }
}

