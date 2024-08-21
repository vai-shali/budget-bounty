package com.example.budget_bounty.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.budget_bounty.exception.InvalidPaymentException;

public class User {
	private static int idCounter = 1;
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
        this.id = idCounter++;
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
        return this.bankDetails;
    }
    
    public void setBankDetails(Bank bankDetails) {
    	this.bankDetails = bankDetails;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public void addTransaction(Transaction transaction) {
        if(transaction != null)
        	transactions.add(transaction);
        else 
        	System.out.println("Cannot add a null transaction!!");
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
    
    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Adjust the format as per your requirement
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Method to schedule a payment with a PaymentScheduler instance
    public void schedulePayment(Bill bill, String paymentDate, PaymentScheduler scheduler) {
    
    	if (bill != null) {
            // Validate paymentDate
            if (isValidDate(paymentDate)) {
                scheduler.addBill(bill);
                System.out.println("Payment of $" + bill.getTotalAmount() + " scheduled for " + paymentDate + " for bill ID: " + bill.getId());
            } else {
                System.out.println("Invalid payment date: " + paymentDate + "\nCannot add bill!!");
         
            }
        } else {
            System.out.println("Cannot schedule null Bill!!");
        }
    }

    // Method to make a payment
    public void makePayment(Bill bill) throws InvalidPaymentException{
    	if(bill.getAmount() <= 0)
    	{
    		throw new InvalidPaymentException("Invalid payment amount!!");
    	}
        System.out.println("Payment of $" + bill.getTotalAmount() + " made for bill ID: " + bill.getId());
    }

    @Override
    public String toString() {
        return "UserName: " + username + "\nEmail: " + email + "\nPhone: " + phone + "\n" 
    + (bankDetails!=null ?bankDetails.toString() : "No Bank details to display");
    }
}