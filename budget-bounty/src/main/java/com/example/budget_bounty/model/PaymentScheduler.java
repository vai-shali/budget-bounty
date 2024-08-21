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
        scheduledBills.add(bill);
    }

    public void removeBill(Bill bill) {
        scheduledBills.remove(bill);
    }
    
    public List<Bill> getBills() {
    	return scheduledBills;
    }

    public void processPayments(User user) throws InvalidPaymentException {
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
