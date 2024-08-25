package com.example.budget_bounty.service;

import com.example.budget_bounty.repository.TransactionRepository;
import model1.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService() {
        this.transactionRepository = new TransactionRepository();
    }

    // Save a new transaction
    public void saveTransaction(Transaction transaction) {
        try {
            transactionRepository.save(transaction);
            System.out.println("Transaction saved successfully.");
        } catch (SQLException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Find a transaction by ID
    public Transaction getTransactionById(int transactionId) {
        try {
            return transactionRepository.findById(transactionId);
        } catch (SQLException e) {
            System.err.println("Error retrieving transaction by ID: " + e.getMessage());
            return null;
        }
    }
    
    // Find transactions by user ID
    public List<Transaction> getTransactionsByUserId(int userId) {
        try {
            return transactionRepository.findByUserId(userId);
        } catch (SQLException e) {
            System.err.println("Error retrieving transactions by user ID: " + e.getMessage());
            return null;
        }
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        try {
            return transactionRepository.findAll();
        } catch (SQLException e) {
            System.err.println("Error retrieving all transactions: " + e.getMessage());
            return null;
        }
    }

    // Update an existing transaction
    public void updateTransaction(Transaction transaction) {
        try {
            transactionRepository.update(transaction);
            System.out.println("Transaction updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating transaction: " + e.getMessage());
        }
    }

    // Delete a transaction by ID
    public void deleteTransaction(int transactionId) {
        try {
            transactionRepository.delete(transactionId);
            System.out.println("Transaction deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting transaction: " + e.getMessage());
        }
    }
}
