package com.example.budget_bounty.model;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Class to store details of a specific transaction.
 * @author Amrisha Das
 * @since 16th Aug,2024.
 */
public class Bill {
    private int id;
    private String paymentMethod;
    private double amount;
    private double tax;
    private double totalAmount;
    private Date dueDate;
    private int userId;
    private boolean paid; //(true/false) new addition during testing phase
    
    /**
     * Constructor to store in details of a transaction.
     * @param id
     * @param paymentMethod
     * @param amount
     * @param tax
     * @param dueDate
     * @param userId
     * @param paid
     * 
     * @throws IllegalArugmentException due date cannot be null and amount cannot be less than 0.
     */
    public Bill(int id, String paymentMethod, double amount, double tax, Date dueDate, int userId, boolean paid) {
    	if(dueDate == null) {
    		throw new IllegalArgumentException("Due Date cannot be null!");
    	}
    	if(amount <= 0) {
    		throw new IllegalArgumentException("Amount cannot be zero or negative!");
    	}
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.tax = tax;
        this.totalAmount = amount + tax;
        this.dueDate = dueDate;
        this.userId = userId;
        this.paid = paid;
    }

    /**
     *  Overloaded constructor with default value for 'paid'
     * 
     * @param id
     * @param paymentMethod
     * @param amount
     * @param tax
     * @param dueDate
     * @param userId
     */
    public Bill(int id, String paymentMethod, double amount, double tax, Date dueDate, int userId) {
        this(id, paymentMethod, amount, tax, dueDate, userId, false); // Default 'paid' is false
    }
    /**
     * 
     * @return int Id for the transaction
     */
    public int getId() {
        return id;
    }
    /**
     * Sets Id for a transaction.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the payment method used for the current transaction.
     * @return String
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }
    /**
     * Set the Payment Method for the transaction.
     * @param paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    /**
     * Returns the transaction amount.
     * @return double
     */
    public double getAmount() {
        return amount;
    }
    /**
     * Sets transaction amount.
     * @param amount
     * @throws IllegalArgumentException when amount is less than 0.
     */
    public void setAmount(double amount) {
    	if(amount <= 0) {
    		throw new IllegalArgumentException("Amount cannot be zero or negative!");
    	}
        this.amount = amount;
        this.totalAmount = amount + this.tax; // Update totalAmount when amount changes
    }
    /**
     * Return the tax paid for transaction.
     * @return double
     */
    public double getTax() {
        return tax;
    }
    /**
     * Sets the tax paid and total bill amount.
     * @param tax
     */
    public void setTax(double tax) {
        this.tax = tax;
        this.totalAmount = this.amount + tax; // Update totalAmount when tax changes
    }
    /**
     * Returns the total amount for the transaction including the tax.
     * @return double 
     */
    public double getTotalAmount() {
        return totalAmount;
    }
    /**
     * Return if the bill is paid or an upcoming transaction.
     * @return boolean
     */
    public boolean getPaid() {
    	return paid;
    }
    /**
     * Sets if the bill is paid or not.
     * @param paid
     */
    public void setPaid(boolean paid) {
    	this.paid = paid;
    }
    /**
     * Return the dueDate for the bill payment.
     * @return
     */
    public Date getDueDate() {
        return dueDate;
    }
    /**
     * Set the due date for the transaction.
     * @param dueDate
     * @throws IllegalArgumentException due date cannot be null.
     */
    public void setDueDate(Date dueDate) {
    	if(dueDate == null) {
    		throw new IllegalArgumentException("Due Date cannot be null!");
    	}
        this.dueDate = dueDate;
    }
    /**
     * return the userId
     * @return int
     */
    public int getUserId() {
        return userId;
    }
	/**
	 *  Sets the userId.
	 * @param userId
	 */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Checks if the bill is paid, overdue or pending and returns the status.
     * @return String
     */
    public String getBillStatus() {
        Date currentDate = new Date();
        if(paid)
        	return "Paid";
        if (currentDate.after(dueDate)) {
            return "Overdue";
        } else {
            return "Pending";
        }
    }
    /**
     * Returns the Detials of the current bill
     * @return String.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Bill{" +
                "id=" + id +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                ", tax=" + tax +
                ", totalAmount=" + totalAmount +
                ", dueDate=" + sdf.format(dueDate) +
                ", userId=" + userId +
                '}';
    }
}
