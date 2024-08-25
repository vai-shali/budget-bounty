package com.example.budget_bounty.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.budget_bounty.exception.InvalidPaymentException;
/**
 * To Store details of each user using Budget Bounty App.
 * @author Amrisha Das
 * @since 16th Aug,2024
 */
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

    /**
     * Constructor to store in details of a user.
     * @param username
     * @param email
     * @param phone
     * @param bankDetails
     * @param password
     */
    public User(int id, String username, String email, String phone, Bank bankDetails, String password) {
        this.id = id;
    	this.username = username;
        this.email = email;
        this.phone = phone;
        this.bankDetails = bankDetails;
        this.password = password;
    }

    /**
     * Default Constructor
     */
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
    /**
     * Returns the bank Details of the current user.
     * @return Bank
     */
    public Bank getBankDetails() {
        return this.bankDetails;
    }
    /**
     * Sets the bank Details of the current user.
     * @param bankDetails
     */
    public void setBankDetails(Bank bankDetails) {
    	this.bankDetails = bankDetails;
    }
    /**
     * returns a list of all the transactions made by this user.
     * @return List<Transaction>
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
    /**
     * Add a new transaction into Users Account.
     * @param transaction
     */
    public void addTransaction(Transaction transaction) {
        if(transaction != null)
        	transactions.add(transaction);
        else 
        	System.out.println("Cannot add a null transaction!!");
    }

    /**
     * Returns the username for the user.
     * @return String 
     */
    public String getUsername() {
        return username;
    }
    /**
     * Set the username for the user.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * return the Id for the current user
     * @return int
     */
    public int getId() {
        return id;
    }
    /**
     * set the id for current user.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns Phone Number
     * @return String
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Sets Phone Number for the user.
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * returns the email Address for the User.
     * @return String
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets a new Email Address for user.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Returns the User Password.
     * @return String
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets a new Password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Checks if the input is a valid Date.
     * @param dateStr
     * @return boolean
     */
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
    /**
     * Method to schedule a payment with a PaymentScheduler instance
     * @param bill
     * @param paymentDate
     * @param scheduler
     */
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

    /**
     * Method to make a payment
     * @param bill
     * @throws InvalidPaymentException if bill amount is less than 0.
     */
    public void makePayment(Bill bill) throws InvalidPaymentException{
    	if(bill.getAmount() <= 0)
    	{
    		throw new InvalidPaymentException("Invalid payment amount!!");
    	}
        System.out.println("Payment of $" + bill.getTotalAmount() + " made for bill ID: " + bill.getId());
    }
    /**
     * Returns the details about the current user.
     * @return String.
     */
    @Override
    public String toString() {
        return "UserName: " + username + "\nEmail: " + email + "\nPhone: " + phone + "\n" 
    + (bankDetails!=null ?bankDetails.toString() : "No Bank details to display");
    }
}