package com.example.budget_bounty.service;

import com.example.budget_bounty.repository.TransactionRepository;
import com.example.budget_bounty.model1.PaymentTransaction;
import java.util.List;

import org.hibernate.HibernateException;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService() {
        this.transactionRepository = new TransactionRepository();
    }
    
    public TransactionService(TransactionRepository trc) {
        this.transactionRepository = trc;
    }
    
    // Save a new transaction
    public void saveTransaction(PaymentTransaction transaction) {
        try {
            transactionRepository.save(transaction);
            System.out.println("Transaction saved successfully.");
        } catch (HibernateException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Find a transaction by ID
    public PaymentTransaction getTransactionById(int transactionId) {
        try {
            return transactionRepository.findById(transactionId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving transaction by ID: " + e.getMessage());
            return null;
        }
    }
    
    // Find transactions by user ID
    public List<PaymentTransaction> getTransactionsByUserId(int userId) {
        try {
            return transactionRepository.findByUserId(userId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving transactions by user ID: " + e.getMessage());
            return null;
        }
    }

    // Get all transactions
    public List<PaymentTransaction> getAllTransactions() {
        try {
            return transactionRepository.findAll();
        } catch (HibernateException e) {
            System.err.println("Error retrieving all transactions: " + e.getMessage());
            return null;
        }
    }

    // Update an existing transaction
    public void updateTransaction(PaymentTransaction transaction) {
        try {
            transactionRepository.update(transaction);
            System.out.println("Transaction updated successfully.");
        } catch (HibernateException e) {
            System.err.println("Error updating transaction: " + e.getMessage());
        }
    }

    // Delete a transaction by ID
    public void deleteTransaction(int transactionId) {
        try {
            transactionRepository.delete(transactionId);
            System.out.println("Transaction deleted successfully.");
        } catch (HibernateException e) {
            System.err.println("Error deleting transaction: " + e.getMessage());
        }
    }
}
