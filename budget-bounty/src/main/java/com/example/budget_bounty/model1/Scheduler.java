package com.example.budget_bounty.model1;

import java.sql.Date;

public class Scheduler {
    private Integer schedulerId;
    private Integer userId;
    private String billType;
    private Integer customerId;
    private String billName;
    private String payeeAcc;
    private double amount;
    private Date dueDate;
    private Date scheduledDate;
    private Integer isRecurring; // Converted from NUMBER(1) to boolean
    private String frequency;
    private Integer endAfter;
    private Date endBy;
    private Integer isPaid; // Converted from NUMBER(1) to boolean

    // Constructor without parameters
    public Scheduler() {}

    // Constructor with parameters
    public Scheduler(Integer schedulerId, Integer userId, String billType, Integer customerId, String billName, 
                     String payeeAcc, double amount, Date dueDate, Date scheduledDate, Integer isRecurring, 
                     String frequency, Integer endAfter, Date endBy, Integer isPaid) {
        this.schedulerId = schedulerId;
        this.userId = userId;
        this.billType = billType;
        this.customerId = customerId;
        this.billName = billName;
        this.payeeAcc = payeeAcc;
        this.amount = amount;
        this.dueDate = dueDate;
        this.scheduledDate = scheduledDate;
        this.isRecurring = isRecurring;
        this.frequency = frequency;
        this.endAfter = endAfter;
        this.endBy = endBy;
        this.isPaid = isPaid;
    }

    // Getters and Setters
    public Integer getSchedulerId() {
        return schedulerId;
    }

    public void setSchedulerId(Integer schedulerId) {
        this.schedulerId = schedulerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getPayeeAcc() {
        return payeeAcc;
    }

    public void setPayeeAcc(String payeeAcc) {
        this.payeeAcc = payeeAcc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Integer isRecurring() {
        return isRecurring;
    }

    public void setRecurring(Integer isRecurring) {
        this.isRecurring = isRecurring;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getEndAfter() {
        return endAfter;
    }

    public void setEndAfter(Integer endAfter) {
        this.endAfter = endAfter;
    }

    public Date getEndBy() {
        return endBy;
    }

    public void setEndBy(Date endBy) {
        this.endBy = endBy;
    }

    public Integer isPaid() {
        return isPaid;
    }

    public void setPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Scheduler [schedulerId=" + schedulerId + ", userId=" + userId + ", billType=" + billType
                + ", customerId=" + customerId + ", billName=" + billName + ", payeeAcc=" + payeeAcc + ", amount="
                + amount + ", dueDate=" + dueDate + ", scheduledDate=" + scheduledDate + ", isRecurring=" + isRecurring
                + ", frequency=" + frequency + ", endAfter=" + endAfter + ", endBy=" + endBy + ", isPaid=" + isPaid + "]";
    }
}

