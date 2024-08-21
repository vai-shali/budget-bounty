package com.example.budget_bounty.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.budget_bounty.exception.InvalidPaymentException;

public class PaymentScheduler {
    private List<Bill> scheduledBills;
    // reminders
    // For each bill- scheduledDate, recurring/non, auto-pay/not

    public PaymentScheduler() {
        this.scheduledBills = new ArrayList<>();
    }

    public void addBill(Bill bill) {
        if (bill == null) {
            throw new IllegalArgumentException("Bill cannot be null");
        }
        scheduledBills.add(bill);
    }

    public void removeBill(Bill bill) {
    	if(scheduledBills.contains(bill))
    		scheduledBills.remove(bill);
    	else {
    		throw new IllegalArgumentException("Bill not found in the scheduled bills list");
    	}
    		
    }
    
    public List<Bill> getBills() {
    	return scheduledBills;
    }

    public void processPayments(User user) throws InvalidPaymentException {
        if(user == null)
        	throw new InvalidPaymentException("User cannot be null!");
    	for (Bill bill : scheduledBills) {
            if (new Date().before(bill.getDueDate())) {
                user.makePayment(bill);
            } else {
                System.out.println("Bill ID: " + bill.getId() + " is overdue. Cannot process payment.");
            }
        }
    }

    public void viewScheduledBills() {
        for (Bill bill : scheduledBills) {
            System.out.println(bill);
        }
    }
}
