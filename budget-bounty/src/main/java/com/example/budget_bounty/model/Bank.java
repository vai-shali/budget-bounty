package com.example.budget_bounty.model;

import java.util.*;
import java.text.SimpleDateFormat;

public class Bank {
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String upiId;
    private double balance;

    public Bank(String bankName, String accountNumber, String ifscCode, double balance) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.balance = balance;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    @Override
    public String toString() {
        return "Bank Name: " + bankName + "\nAccount Number: " + accountNumber + "\nIFSC Code: " + ifscCode + (upiId != null ? "\nUPI ID: " + upiId : "");
    }
}


