package com.example.budget_bounty.model;

import java.util.*;
import java.text.*;

public class Transaction {
    private String transactionFrom;
    private String transactionTo;
    private double amount;
    private Date transactionDate;
    private String referenceNumber;

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

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Transaction From: " + transactionFrom + "\nTransaction To: " + transactionTo + "\nAmount: " + amount + 
               "\nDate: " + dateFormat.format(transactionDate) + "\nReference Number: " + referenceNumber;
    }
}

