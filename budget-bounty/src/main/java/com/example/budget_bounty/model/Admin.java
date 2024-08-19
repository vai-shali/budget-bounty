package com.example.budget_bounty.model;

public class Admin {
	private PaymentScheduler paymentScheduler;

    public Admin() {
        this.paymentScheduler = new PaymentScheduler();
    }

    public void scheduleBill(Bill bill) {
        paymentScheduler.addBill(bill);
        System.out.println("Bill ID: " + bill.getId() + " has been scheduled.");
    }

    public void unscheduleBill(Bill bill) {
        paymentScheduler.removeBill(bill);
        System.out.println("Bill ID: " + bill.getId() + " has been removed from the schedule.");
    }

    public void viewAllScheduledBills() {
        System.out.println("Scheduled Bills:");
        paymentScheduler.viewScheduledBills();
    }

    public void processAllPayments(User user) {
        paymentScheduler.processPayments(user);
    }
}
