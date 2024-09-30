package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.TransactionNotFoundException;
import com.example.demo.model.PaymentTransaction;
import com.example.demo.service.PaymentTransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class PaymentTransactionController {

    private final PaymentTransactionService paymentTransactionService;

    @Autowired
    public PaymentTransactionController(PaymentTransactionService paymentTransactionService) {
        this.paymentTransactionService = paymentTransactionService;
    }

    /**
     * Creates a new payment transaction.
     *
     * @param transaction the PaymentTransaction object to be saved
     * @return a response indicating success or failure
     */
    @PostMapping("/add")
    public ResponseEntity<String> createTransaction(@RequestBody PaymentTransaction transaction) {
        paymentTransactionService.saveTransaction(transaction);
        return ResponseEntity.status(201).body("Transaction created successfully.");
    }

    /**
     * Retrieves a payment transaction by its ID.
     *
     * @param transactionId the ID of the transaction to retrieve
     * @return the PaymentTransaction object with the specified ID
     */
    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(@PathVariable int transactionId) {
    	try {
    		PaymentTransaction transaction = paymentTransactionService.getTransactionById(transactionId);
            return ResponseEntity.ok(transaction);
    	} catch(TransactionNotFoundException e) {
    		return ResponseEntity.status(404).body(e.getMessage()); 
    	}
        
    }

    /**
     * Retrieves all payment transactions for a specific user.
     *
     * @param userId the ID of the user whose transactions are to be retrieved
     * @return a list of PaymentTransaction objects associated with the specified user ID
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTransactionsByUserId(@PathVariable int userId) {
    	try {
    		List<PaymentTransaction> transactions = paymentTransactionService.getTransactionsByUserId(userId);
            return ResponseEntity.ok(transactions);
    	} catch(TransactionNotFoundException e) {
    		return ResponseEntity.status(404).body(e.getMessage()); 
    	}
        
    }

    /**
     * Retrieves all payment transactions.
     *
     * @return a list of all PaymentTransaction objects
     */
    @GetMapping("/list")
    public ResponseEntity<List<PaymentTransaction>> getAllTransactions() {
        List<PaymentTransaction> transactions = paymentTransactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    /**
     * Updates an existing payment transaction.
     *
     * @param transactionId the ID of the transaction to be updated
     * @param updatedTransaction the PaymentTransaction object with updated information
     * @return a response indicating success or failure
     */
    @PutMapping("/update/{transactionId}")
    public ResponseEntity<String> updateTransaction(@PathVariable int transactionId,
                                                    @RequestBody PaymentTransaction updatedTransaction) {
        try {
            updatedTransaction.setTransactionId(transactionId); // Ensure correct ID is set
            paymentTransactionService.updateTransaction(updatedTransaction);
            return ResponseEntity.ok("Transaction updated successfully.");
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    /**
     * Deletes a payment transaction by its ID.
     *
     * @param transactionId the ID of the transaction to be deleted
     * @return a response indicating success or failure
     */
    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int transactionId) {
        try {
            paymentTransactionService.deleteTransaction(transactionId);
            return ResponseEntity.ok("Transaction deleted successfully.");
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}