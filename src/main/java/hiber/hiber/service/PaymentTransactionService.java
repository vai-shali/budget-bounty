package hiber.hiber.service;

import java.util.List;

import org.hibernate.HibernateException;

import hiber.hiber.model.PaymentTransaction;
import hiber.hiber.repository.PaymentTransactionRepository;

/**
 * Service class for managing payment transactions.
 * Provides methods to perform CRUD operations on payment transactions.
 */
public class PaymentTransactionService {
    private final PaymentTransactionRepository paymentTransactionRepository;

    /**
     * Default constructor to initialize the PaymentTransactionRepository.
     * Creates a new instance of PaymentTransactionRepository.
     */
    public PaymentTransactionService() {
        this.paymentTransactionRepository = new PaymentTransactionRepository();
    }

    /**
     * Constructor to initialize the PaymentTransactionRepository with a given instance.
     *
     * @param trc the PaymentTransactionRepository instance to use
     */
    public PaymentTransactionService(PaymentTransactionRepository trc) {
        this.paymentTransactionRepository = trc;
    }

    /**
     * Saves a new payment transaction.
     *
     * @param transaction the PaymentTransaction object to be saved
     */
    public void saveTransaction(PaymentTransaction transaction) {
        try {
            paymentTransactionRepository.save(transaction);
            System.out.println("Transaction saved successfully.");
        } catch (HibernateException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    /**
     * Finds a payment transaction by its ID.
     *
     * @param transactionId the ID of the transaction to retrieve
     * @return the PaymentTransaction object with the specified ID, or null if not found
     */
    public PaymentTransaction getTransactionById(int transactionId) {
        try {
            return paymentTransactionRepository.findById(transactionId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving transaction by ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Finds payment transactions by user ID.
     *
     * @param userId the ID of the user whose transactions are to be retrieved
     * @return a list of PaymentTransaction objects associated with the specified user ID, or null if an error occurs
     */
    public List<PaymentTransaction> getTransactionsByUserId(int userId) {
        try {
            return paymentTransactionRepository.findByUserId(userId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving transactions by user ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves all payment transactions.
     *
     * @return a list of all PaymentTransaction objects, or null if an error occurs
     */
    public List<PaymentTransaction> getAllTransactions() {
        try {
            return paymentTransactionRepository.findAll();
        } catch (HibernateException e) {
            System.err.println("Error retrieving all transactions: " + e.getMessage());
            return null;
        }
    }

    /**
     * Updates an existing payment transaction.
     *
     * @param transaction the PaymentTransaction object with updated information
     */
    public void updateTransaction(PaymentTransaction transaction) {
        try {
            paymentTransactionRepository.update(transaction);
            System.out.println("Transaction updated successfully.");
        } catch (HibernateException e) {
            System.err.println("Error updating transaction: " + e.getMessage());
        }
    }

    /**
     * Deletes a payment transaction by its ID.
     *
     * @param transactionId the ID of the transaction to be deleted
     */
    public void deleteTransaction(int transactionId) {
        try {
            paymentTransactionRepository.delete(transactionId);
            System.out.println("Transaction deleted successfully.");
        } catch (HibernateException e) {
            System.err.println("Error deleting transaction: " + e.getMessage());
        }
    }
}
