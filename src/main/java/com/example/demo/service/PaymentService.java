package com.example.demo.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Bank;
import com.example.demo.model.Notification;
import com.example.demo.model.PaymentTransaction;
import com.example.demo.model.Scheduler;
import com.example.demo.model.User;

@Service
public class PaymentService {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private BankService bankService;

    @Autowired
    private PaymentTransactionService transactionService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RewardService rewardService;

    @Transactional
    public void makePayment() {
        // 1. Get today's scheduled payments
        List<Scheduler> duePayments = schedulerService.getScheduledPaymentsForToday();
        if(duePayments.size()==0)
        	System.out.println("NO PAYMENT TODAY");
		for(Scheduler s:duePayments)
		{
			System.out.println(s);
		}

        for (Scheduler scheduler : duePayments) {
            // 2. Check user's bank balance
        	System.out.println("STEP 2\n-------------------------------------");
            User user = scheduler.getUser();
            Bank bank = user.getBankDetails();
            
            if (bank.getBalance() >= scheduler.getAmount()) {
                // 3. Deduct balance and process payment
            	System.out.println("STEP 3\n-------------------------------------");
                bankService.updateBalance(user.getUserId(), bank.getBalance() - scheduler.getAmount());
                
                // 4. Record the transaction
                System.out.println("STEP 4\n-------------------------------------");
                String transactionName = scheduler.getBillName();
                String fromAccountNumber = bank.getAccountNumber()!=null?bank.getAccountNumber():bank.getUpiId();
                String toAccountNumber = scheduler.getPayeeAcc();
                String transactionType = "debit";
                Double amount = scheduler.getAmount();
                String status = "success";
                PaymentTransaction paymentTransaction = new PaymentTransaction(
                		user, new Date(), transactionName,
                        fromAccountNumber, toAccountNumber, transactionType,
                        amount, UUID.randomUUID().toString(), status
                		);
                transactionService.saveTransaction(paymentTransaction);
                
                // 5. Save/Send notification
                System.out.println("STEP 5\n-------------------------------------");
                String message = "Your scheduled payment of " + scheduler.getAmount() + " for " + scheduler.getBillName() + " was successful.";
                Notification notif = new Notification(user, scheduler, "Payment success", message, new Date(), 0, 0);
                notificationService.createNotification(notif, user.getUserId());
                
                // 6. Update reward points
                System.out.println("STEP 6\n-------------------------------------");
                int points = (int) (amount/10);
                rewardService.addPoints(points, user.getUserId()); // Add reward points for successful payment
                
                // 9. Handle recurring or one-time payments
                System.out.println("STEP 9\n-------------------------------------");
                if (scheduler.getIsRecurring()==1) {
                	System.out.println("Recurring");
                    schedulerService.updateSchedulerAfterPayment(scheduler);
                } else {
                    schedulerService.markPaymentAsCompleted(scheduler);
                }

            } else {
                // 7. Handle insufficient balance
            	System.out.println("STEP 7\n-------------------------------------");
            	String transactionName = scheduler.getBillName();
                String fromAccountNumber = bank.getAccountNumber()!=null?bank.getAccountNumber():bank.getUpiId();
                String toAccountNumber = scheduler.getPayeeAcc();
                String transactionType = "debit";
                Double amount = scheduler.getAmount();
                String status = "failed";
                PaymentTransaction paymentTransaction = new PaymentTransaction(
                		user, new Date(), transactionName,
                        fromAccountNumber, toAccountNumber, transactionType,
                        amount, UUID.randomUUID().toString(), status
                		);
                transactionService.saveTransaction(paymentTransaction);
                
                // 8. Add error message in notification table
                System.out.println("STEP 8\n-------------------------------------");
                String message = "Your scheduled payment of " + scheduler.getAmount() + " for " + scheduler.getBillName() + " failed due to insufficient bank balance!";
                Notification notif = new Notification(user, scheduler, "Payment failed", message, new Date(), 0, 0);
                notificationService.createNotification(notif, user.getUserId());
                
                // 9. Handle recurring or one-time payments
                System.out.println("STEP 9\n-------------------------------------");
                if (scheduler.getIsRecurring()==1) {
                	System.out.println("Recurring");
                    schedulerService.updateSchedulerAfterPaymentFail(scheduler);
                } else {
                    schedulerService.markPaymentAsCancelled(scheduler);
                }
            }
        } 
    }
    
    @Transactional 
    public void addReminder() {
    	//check which payments are due 2 days from today
    	//add reminder- insert a record in notifs table with req info- which payment, how much
    	//if bank balance low, alert the user
    	
    	Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        
        Date twoDaysFromToday = calendar.getTime();

        // Fetch payments due in 2 days
        List<Scheduler> duePayments = schedulerService.findByScheduledDate(twoDaysFromToday);
        if(duePayments.size()==0) {
        	System.out.println("No reminders to send");
        }
        
        for (Scheduler payment : duePayments) {
        	User user = payment.getUser();
            // Insert a record in the notifs table
            Notification notif = new Notification();
            String message;
            // Check if the bank balance is low
            Bank bank = bankService.getBankByUserId(payment.getUser().getUserId());
            if (bank != null && bank.getBalance() < payment.getAmount()) {
            	message="Your Bank account has low balance! Scheduled Payment for "+payment.getBillName()+", due in 2 days!";
            }
            else {
            	message="Your Scheduled Payment for "+payment.getBillName()+", due in 2 days!";
            }
            notif = new Notification(user, payment, "Reminder", message, new Date(), 0, 0);
            notif.setScheduler(payment);
            notificationService.createNotification(notif, user.getUserId());
        }
    }
}
