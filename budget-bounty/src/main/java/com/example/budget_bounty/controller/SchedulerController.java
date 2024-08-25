package com.example.budget_bounty.controller;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.example.budget_bounty.service.SchedulerService;

import model1.Scheduler;

public class SchedulerController {

    private SchedulerService schedulerService = new SchedulerService();
    Scanner scanner= new Scanner(System.in);
    int userId;

    public SchedulerController(int userId) {
        this.userId=userId;
    }

    public void handleUserInput() {
        while (true) {
            System.out.println("Scheduler Menu:");
//            System.out.println("1. View all scheduled payments");
            System.out.println("1. View all your scheduled payments");
            System.out.println("2. View your scheduled payments by bill name");
            System.out.println("3. Add a new scheduled payment");
            System.out.println("4. Update an existing scheduled payment");
            System.out.println("5. Delete a scheduled payment by bill name");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
//                case 1:
//                    viewAllScheduledPayments();
//                    break;
                case 1:
                    viewScheduledPaymentsByUserId();
                    break;
                case 2:
                    viewScheduledPaymentsByBillName();
                    break;
                case 3:
                    addScheduler();
                    break;
                case 4:
                    updateScheduler();
                    break;
                case 5:
                    deleteScheduler();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

//    private void viewAllScheduledPayments() {
//        List<Scheduler> schedulers = schedulerService.getAllScheduledPayments();
//        if (schedulers.isEmpty()) {
//            System.out.println("No scheduled payments found.");
//        } else {
//            schedulers.forEach(System.out::println);
//        }
//    }

    private void viewScheduledPaymentsByUserId() {
        List<Scheduler> schedulers = schedulerService.getSchedulersByUserId(userId);
        if (schedulers.isEmpty()) {
            System.out.println("No scheduled payments found for user ID " + userId);
        } else {
            schedulers.forEach(System.out::println);
        }
    }

    private void viewScheduledPaymentsByBillName() {
        System.out.print("Enter bill name: ");
        String billName = scanner.nextLine();
        List<Scheduler> schedulers = schedulerService.getSchedulersByBillName(userId, billName);
        if (schedulers.isEmpty()) {
            System.out.println("No scheduled payments found for bill name '" + billName + "' and user ID " + userId);
        } else {
            schedulers.forEach(System.out::println);
        }
    }

    private void addScheduler() {
    	System.out.print("Enter scheduler ID: ");
        int schedulerId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter bill type: ");
        String billType = scanner.nextLine();
        System.out.print("Enter customer ID (or leave empty if none): ");
        String customerId1 = scanner.nextLine().trim();
        Integer customerId = customerId1.isEmpty() ? null : Integer.parseInt(customerId1);
        scanner.nextLine();
        System.out.print("Enter bill name: ");
        String billName = scanner.nextLine();
        System.out.print("Enter payee account: ");
        String payeeAcc = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        Date dueDate = Date.valueOf(scanner.next());
        System.out.print("Enter scheduled date (YYYY-MM-DD): ");
        Date scheduledDate = Date.valueOf(scanner.next());
        System.out.print("Is recurring (1-true/0-false): ");
        Integer isRecurring = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter frequency (e.g., monthly, quarterly): ");
        String frequency = scanner.nextLine();
        System.out.print("Enter end after (number of payments) or leave blank: ");
        String endAfter1 = scanner.nextLine().trim();
        Integer endAfter = endAfter1.isEmpty() ? null : Integer.parseInt(endAfter1);
        scanner.nextLine();
        System.out.print("Enter end by date (YYYY-MM-DD) or leave blank: ");
        String endByStr = scanner.nextLine();
        Date endBy = endByStr.isEmpty() ? null : Date.valueOf(endByStr);
        System.out.print("Is paid (1-true/0-false): ");
        Integer isPaid = scanner.nextInt();

        Scheduler scheduler = new Scheduler(schedulerId, userId, billType, customerId, billName, payeeAcc, amount, dueDate, scheduledDate, isRecurring, frequency, endAfter, endBy, isPaid);
        schedulerService.addScheduler(scheduler, userId);
        System.out.println("Scheduler added successfully.");
    }

    private void updateScheduler() {
        System.out.print("Enter scheduler ID: ");
        Integer schedulerId = scanner.nextInt();
        scanner.nextLine();
//        System.out.print("Enter user ID: ");
//        int userId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
        System.out.print("Enter bill type: ");
        String billType = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        Integer customerId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter bill name: ");
        String billName = scanner.nextLine();
        System.out.print("Enter payee account: ");
        String payeeAcc = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        Date dueDate = Date.valueOf(scanner.next());
        System.out.print("Enter scheduled date (YYYY-MM-DD): ");
        Date scheduledDate = Date.valueOf(scanner.next());
        System.out.print("Is recurring (true/false): ");
        Integer isRecurring = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter frequency (e.g., monthly, quarterly): ");
        String frequency = scanner.nextLine();
        System.out.print("Enter end after (number of payments) or 0: ");
        Integer endAfter = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter end by date (YYYY-MM-DD) or leave blank: ");
        String endByStr = scanner.nextLine();
        Date endBy = endByStr.isEmpty() ? null : Date.valueOf(endByStr);
        System.out.print("Is paid (true/false): ");
        Integer isPaid = scanner.nextInt();

        Scheduler scheduler = new Scheduler(schedulerId, userId, billType, customerId, billName, payeeAcc, amount, dueDate, scheduledDate, isRecurring, frequency, endAfter, endBy, isPaid);
        schedulerService.updateScheduler(scheduler, userId);
        System.out.println("Scheduler updated successfully.");
    }

    private void deleteScheduler() {
        System.out.print("Enter bill name: ");
        String billName = scanner.nextLine();
        schedulerService.deleteSchedulerByBillName(userId, billName);
        System.out.println("Scheduler deleted successfully.");
    }
}
