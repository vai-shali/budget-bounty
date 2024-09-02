package hiber.hiber.service;

import java.util.List;

import org.hibernate.HibernateException;

import hiber.hiber.model.PaymentTransaction;
import hiber.hiber.repository.PaymentTransactionRepository;

public class PaymentTransactionService {
	private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentTransactionService() {
        this.paymentTransactionRepository = new PaymentTransactionRepository();
    }
    
    public PaymentTransactionService(PaymentTransactionRepository trc) {
        this.paymentTransactionRepository = trc;
    }
    
    // Save a new transaction
    public void saveTransaction(PaymentTransaction transaction) {
        try {
        	paymentTransactionRepository.save(transaction);
            System.out.println("Transaction saved successfully.");
        } catch (HibernateException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Find a transaction by ID
    public PaymentTransaction getTransactionById(int transactionId) {
        try {
            return paymentTransactionRepository.findById(transactionId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving transaction by ID: " + e.getMessage());
            return null;
        }
    }
    
    // Find transactions by user ID
    public List<PaymentTransaction> getTransactionsByUserId(int userId) {
        try {
            return paymentTransactionRepository.findByUserId(userId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving transactions by user ID: " + e.getMessage());
            return null;
        }
    }

    // Get all transactions
    public List<PaymentTransaction> getAllTransactions() {
        try {
            return paymentTransactionRepository.findAll();
        } catch (HibernateException e) {
            System.err.println("Error retrieving all transactions: " + e.getMessage());
            return null;
        }
    }

    // Update an existing transaction
    public void updateTransaction(PaymentTransaction transaction) {
        try {
        	paymentTransactionRepository.update(transaction);
            System.out.println("Transaction updated successfully.");
        } catch (HibernateException e) {
            System.err.println("Error updating transaction: " + e.getMessage());
        }
    }

    // Delete a transaction by ID
    public void deleteTransaction(int transactionId) {
        try {
        	paymentTransactionRepository.delete(transactionId);
            System.out.println("Transaction deleted successfully.");
        } catch (HibernateException e) {
            System.err.println("Error deleting transaction: " + e.getMessage());
        }
    }
}
