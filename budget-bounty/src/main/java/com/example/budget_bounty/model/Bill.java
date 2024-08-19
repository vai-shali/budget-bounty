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

    public Bill(int id, String paymentMethod, double amount, double tax, Date dueDate, int userId) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.tax = tax;
        this.totalAmount = amount + tax;
        this.dueDate = dueDate;
        this.userId = userId;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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
        if (currentDate.after(dueDate)) {
            return "Overdue";
        } else {
            return "Pending";
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
