package com.example.budget_bounty.model;

import com.example.budget_bounty.exception.InvalidPaymentException;
/**
 * All Admin tasks are performed.
 * @author Kiranmoy
 * @since 16th Aug,2024
 */
public class Admin {
	private PaymentScheduler paymentScheduler;
	/**
	 * Creates a singular paymentScheduler.
	 */
    public Admin() {
        this.paymentScheduler = new PaymentScheduler();
    }
    /**
     * Return the Scheduler
     * @return PaymentScheduler.
     */
    public PaymentScheduler getPaymentScheduler() {
    	return paymentScheduler;
    }
    /**
     * Schedules a upcoming bill.
     * @param bill
     */
    public void scheduleBill(Bill bill) {
        paymentScheduler.addBill(bill);
        System.out.println("Bill ID: " + bill.getId() + " has been scheduled.");
    }
    /**
     * Stops a Scheduled bill.
     * @param bill
     */
    public void unscheduleBill(Bill bill) {
        paymentScheduler.removeBill(bill);
        System.out.println("Bill ID: " + bill.getId() + " has been removed from the schedule.");
    }
    /**
     * Prints all the upcoming bills.
     */
    public void viewAllScheduledBills() {
        System.out.println("Scheduled Bills:");
        paymentScheduler.viewScheduledBills();
    }
    /**
     * Process all the payments for a user.
     * @param user
     * @throws InvalidPaymentException
     */
    public void processAllPayments(User user) throws InvalidPaymentException {
        paymentScheduler.processPayments(user);
    }
}
