package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.TransactionNotFoundException;
import com.example.demo.model.PaymentTransaction;
import com.example.demo.repository.PaymentTransactionRepository;

/**
 * Service class for managing payment transactions.
 * Provides methods to perform CRUD operations on payment transactions.
 * @author Vaishali
 * @since 2nd September, 2024
 */
@Service
public class PaymentTransactionService {
	
	@Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    /**
     * Saves a new payment transaction.
     *
     * @param transaction the PaymentTransaction object to be saved
     */
    public void saveTransaction(PaymentTransaction transaction) {
    	paymentTransactionRepository.save(transaction);
        System.out.println("Transaction saved successfully.");
    }

    /**
     * Finds a payment transaction by its ID.
     *
     * @param transactionId the ID of the transaction to retrieve
     * @return the PaymentTransaction object with the specified ID, or null if not found
     */
    public PaymentTransaction getTransactionById(int transactionId) {
    	return paymentTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with ID: " + transactionId));
    }

    /**
     * Finds payment transactions by user ID.
     *
     * @param userId the ID of the user whose transactions are to be retrieved
     * @return a list of PaymentTransaction objects associated with the specified user ID, or null if an error occurs
     */
    public List<PaymentTransaction> getTransactionsByUserId(int userId) {
    	List<PaymentTransaction> transactions = paymentTransactionRepository.findByUserUserId(userId);
    	if(transactions.size()==0) {
    		throw new TransactionNotFoundException("Transactions not found for User ID: " + userId);
    	}
    	return transactions;
    }

    /**
     * Retrieves all payment transactions.
     *
     * @return a list of all PaymentTransaction objects, or null if an error occurs
     */
    public List<PaymentTransaction> getAllTransactions() {
        List<PaymentTransaction> transactions = paymentTransactionRepository.findAll();
    	if(transactions.size()==0) {
    		throw new TransactionNotFoundException("No transactions found!");
    	}
    	return transactions;
    }

    /**
     * Updates an existing payment transaction.
     *
     * @param transaction the PaymentTransaction object with updated information
     */
    public void updateTransaction(PaymentTransaction transaction) {
    	if (!paymentTransactionRepository.existsById(transaction.getTransactionId())) {
            throw new TransactionNotFoundException("Transaction not found with ID: " + transaction.getTransactionId());
        }
        paymentTransactionRepository.save(transaction);
        System.out.println("Transaction updated successfully.");
    }

    /**
     * Deletes a payment transaction by its ID.
     *
     * @param transactionId the ID of the transaction to be deleted
     */
    public void deleteTransaction(int transactionId) {
    	try {
            paymentTransactionRepository.deleteById(transactionId);
            System.out.println("Transaction deleted successfully.");
        } catch (EmptyResultDataAccessException e) {
            throw new TransactionNotFoundException("Transaction not found with ID: " + transactionId);
        }
    }
}
