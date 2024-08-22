package com.example.budget_bounty.model;

import java.util.*;
import java.text.*;
/**
 * Contains the Transaction detials 
 * @author Kiranmoy
 * @since 16th Aug,2024.
 */
public class Transaction {
    private String transactionFrom;
    private String transactionTo;
    private double amount;
    private Date transactionDate;
    private String referenceNumber;
    /**
     * Constructor to Store information
     * @param transactionFrom
     * @param transactionTo
     * @param amount
     * @param transactionDate
     * @param referenceNumber
     */
    public Transaction(String transactionFrom, String transactionTo, double amount, Date transactionDate, String referenceNumber) {
    	if(transactionFrom == null)
    	{
    		throw new IllegalArgumentException("transactionFrom cannot be null");
    	}
    	if(transactionTo == null)
    	{
    		throw new IllegalArgumentException("transactionTo cannot be null");
    	}
    	if(transactionDate == null)
    	{
    		throw new IllegalArgumentException("transactionDate cannot be null");
    	}
        this.transactionFrom = transactionFrom;
        this.transactionTo = transactionTo;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.referenceNumber = referenceNumber;
    }
    /**
     * Returns the detials about the current transaction.
     * @return String.
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Transaction From: " + transactionFrom + "\nTransaction To: " + transactionTo + "\nAmount: " + amount + 
               "\nDate: " + dateFormat.format(transactionDate) + "\nReference Number: " + referenceNumber;
    }
}

