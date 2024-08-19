package com.example.budget_bounty.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentScheduler {
    private List<Bill> scheduledBills;

    public PaymentScheduler() {
        this.scheduledBills = new ArrayList<>();
    }

    public void addBill(Bill bill) {
        scheduledBills.add(bill);
    }

    public void removeBill(Bill bill) {
        scheduledBills.remove(bill);
    }

    public void processPayments(User user) {
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
