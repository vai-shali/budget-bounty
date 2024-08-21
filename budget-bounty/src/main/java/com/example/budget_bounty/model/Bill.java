package com.example.budget_bounty.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
    private int id;
    private String paymentMethod;
    private double amount;
    private double tax;
    private double totalAmount;
    private Date dueDate;
    private int userId;
    private boolean paid; //(true/false) new addition during testing phase
    

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

    // Overloaded constructor with default value for 'paid'
    public Bill(int id, String paymentMethod, double amount, double tax, Date dueDate, int userId) {
        this(id, paymentMethod, amount, tax, dueDate, userId, false); // Default 'paid' is false
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
    	if(amount <= 0) {
    		throw new IllegalArgumentException("Amount cannot be zero or negative!");
    	}
        this.amount = amount;
        this.totalAmount = amount + this.tax; // Update totalAmount when amount changes
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
        this.totalAmount = this.amount + tax; // Update totalAmount when tax changes
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    
    public boolean getPaid() {
    	return paid;
    }
    
    public void setPaid(boolean paid) {
    	this.paid = paid;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
    	if(dueDate == null) {
    		throw new IllegalArgumentException("Due Date cannot be null!");
    	}
        this.dueDate = dueDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
