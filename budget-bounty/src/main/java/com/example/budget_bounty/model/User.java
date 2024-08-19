package com.example.budget_bounty.model;

import java.util.*;

public class User {
//    private String firstName;
//    private String lastName;
    private String username;
    private int id;
    private String phone;
    private String email;
    private String password;
    private Bank bankDetails;
    private List<Transaction> transactions = new ArrayList<>();


    public User(String username, String email, String phone, Bank bankDetails, String password) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.bankDetails = bankDetails;
        this.password = password;
    }


    public User() {}

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    public Bank getBankDetails() {
        return bankDetails;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to schedule a payment with a PaymentScheduler instance
    public void schedulePayment(Bill bill, String paymentDate, PaymentScheduler scheduler) {
        scheduler.addBill(bill);
        System.out.println("Payment of $" + bill.getTotalAmount() + " scheduled for " + paymentDate + " for bill ID: " + bill.getId());
    }

    // Method to make a payment
    public void makePayment(Bill bill) {
        System.out.println("Payment of $" + bill.getTotalAmount() + " made for bill ID: " + bill.getId());
    }

    @Override
    public String toString() {
        return "UserName: " + username + "\nEmail: " + email + "\nPhone: " + phone + "\n" + bankDetails.toString();
    }
}