package com.example.budget_bounty.model;

import java.util.*;
import java.text.SimpleDateFormat;
/**
 * Bank Details associated to a particular user.
 * @author Amrisha Das
 * @since 16th Aug,2024
 */
public class Bank {
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String upiId;
    private String email;
    private double balance;
    /**
     * Constructor to store bank details.
     * @param bankName
     * @param accountNumber
     * @param ifscCode
     * @param balance
     * @param email
     */
    public Bank(String bankName, String accountNumber, String ifscCode, double balance, String email) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.balance = balance;
        this.email = email;
    }
    /**
     * Set up UPI Id associated to the bank account.
     * @param upiId
     */
    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }
    /**
     * Returns the current balance of the account
     * @return double Balance
     */
    public double getBalance() {
        return balance;
    }
    /**
     * Used to change Balance of the account
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    /**
     * Returns the email address linked to the bank account
     * @return String Email Address.
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * Used to change Email Address associated to the bank
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Prints the Details about the current Bank account object.
     * @return String 
     */
    @Override
    public String toString() {
        return "Bank Name: " + bankName + "\nAccount Number: " + accountNumber + "\nIFSC Code: " + ifscCode + (upiId != null ? "\nUPI ID: " + upiId : "");
    }
}


