package hiber.hiber.controller;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import hiber.hiber.model.Scheduler;
import hiber.hiber.model.User;
import hiber.hiber.service.SchedulerService;


public class SchedulerController {

    private SchedulerService schedulerService = new SchedulerService();
    private Scanner scanner = new Scanner(System.in);
    private User user;

    public SchedulerController(User user) {
        this.user = user;
    }

    public void handleUserInput() {
        while (true) {
            System.out.println("Scheduler Menu:");
            System.out.println("1. View all your scheduled payments");
            System.out.println("2. View your scheduled payments by bill name");
            System.out.println("3. Add a new scheduled payment");
            System.out.println("4. Update an existing scheduled payment");
            System.out.println("5. Delete a scheduled payment by bill name");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewScheduledPaymentsByUser();
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

    private void viewScheduledPaymentsByUser() {
        List<Scheduler> schedulers = schedulerService.getSchedulersByUserId(user.getUserId());
        if (schedulers.isEmpty()) {
            System.out.println("No scheduled payments found for user " + user.getUsername());
        } else {
            schedulers.forEach(System.out::println);
        }
    }

    private void viewScheduledPaymentsByBillName() {
        System.out.print("Enter bill name: ");
        String billName = scanner.nextLine();
        List<Scheduler> schedulers = schedulerService.getSchedulersByBillName(user, billName);
        if (schedulers.isEmpty()) {
            System.out.println("No scheduled payments found for bill name '" + billName + "' and user " + user.getUsername());
        } else {
            schedulers.forEach(System.out::println);
        }
    }

    private void addScheduler() {
    	String billType;
        Integer customerId = null;
        String billName;
        String payeeAcc;
        double amount;
        Date dueDate = null;
        Date scheduledDate = null;
        Integer isRecurring;
        String frequency;
        Integer endAfter = null;
        Date endBy = null;
        Integer isPaid;
        
        System.out.print("Enter bill type: ");
        billType = scanner.nextLine();
        
        System.out.print("Enter customer ID (or leave empty if none): ");
        String customerIdStr = scanner.nextLine().trim();
        if (!customerIdStr.isEmpty()) {
            try {
                customerId = Integer.parseInt(customerIdStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid customer ID. Using null.");
            }
        }
        
        System.out.print("Enter bill name: ");
        billName = scanner.nextLine();
        
        System.out.print("Enter payee account: ");
        payeeAcc = scanner.nextLine();
        
        System.out.print("Enter amount: ");
        while (true) {
            try {
                amount = scanner.nextDouble();
                break; // exit loop if no exception
            } catch (InputMismatchException e) {
                System.out.println("Invalid amount. Please enter a numeric value.");
                scanner.next(); // clear the invalid input
            }
        }
        
        scanner.nextLine(); // consume the newline left by nextDouble()

        // Input and validation for due date
        while (dueDate == null) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            try {
                dueDate = Date.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
        
        // Input and validation for scheduled date
        while (scheduledDate == null) {
            System.out.print("Enter scheduled date (YYYY-MM-DD): ");
            try {
                scheduledDate = Date.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        // Input and validation for isRecurring
        while (true) {
            System.out.print("Is recurring (1-true/0-false): ");
            try {
                isRecurring = scanner.nextInt();
                if (isRecurring == 0 || isRecurring == 1) break;
                System.out.println("Invalid input. Please enter 1 for true or 0 for false.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 for true or 0 for false.");
                scanner.next(); // clear the invalid input
            }
        }

        scanner.nextLine(); // consume the newline left by nextInt()

        System.out.print("Enter frequency (e.g., monthly, quarterly): ");
        frequency = scanner.nextLine();

        // Input and validation for endAfter
        while (true) {
            System.out.print("Enter end after (number of payments) or leave blank: ");
            String endAfterStr = scanner.nextLine().trim();
            if (endAfterStr.isEmpty()) {
                endAfter = null;
                break;
            }
            try {
                endAfter = Integer.parseInt(endAfterStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer or leave blank.");
            }
        }

        // Input and validation for endBy date
        while (endBy == null) {
            System.out.print("Enter end by date (YYYY-MM-DD) or leave blank: ");
            String endByStr = scanner.nextLine().trim();
            if (endByStr.isEmpty()) {
                endBy = null;
                break;
            }
            try {
                endBy = Date.valueOf(endByStr);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        // Input and validation for isPaid
        while (true) {
            System.out.print("Is paid (1-true/0-false): ");
            try {
                isPaid = scanner.nextInt();
                if (isPaid == 0 || isPaid == 1) break;
                System.out.println("Invalid input. Please enter 1 for true or 0 for false.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 for true or 0 for false.");
                scanner.next(); // clear the invalid input
            }
        }

        Scheduler scheduler = new Scheduler(user, billType, customerId, billName, payeeAcc, amount, dueDate, scheduledDate, isRecurring, frequency, endAfter, endBy, isPaid);
        schedulerService.addScheduler(scheduler, user);
        System.out.println("Scheduler added successfully.");
    }

    private void updateScheduler() {
        System.out.print("Enter scheduler ID to be updated: ");
        Integer schedulerId = scanner.nextInt();
        scanner.nextLine();

        // Fetch the existing scheduler by ID
        Scheduler existingScheduler = schedulerService.getSchedulerById(schedulerId);
        if (existingScheduler == null) {
            System.out.println("Scheduler with ID " + schedulerId + " does not exist.");
            return;
        }
        
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
        
        existingScheduler.setBillType(billType);
        existingScheduler.setCustomerId(customerId);
        existingScheduler.setBillName(billName);
        existingScheduler.setPayeeAcc(payeeAcc);
        existingScheduler.setAmount(amount);
        existingScheduler.setDueDate(dueDate);
        existingScheduler.setScheduledDate(scheduledDate);
        existingScheduler.setRecurring(isRecurring);
        existingScheduler.setFrequency(frequency);
        existingScheduler.setEndAfter(endAfter);
        existingScheduler.setEndBy(endBy);
        existingScheduler.setPaid(isPaid);

        schedulerService.updateScheduler(existingScheduler, user);

        System.out.println("Scheduler updated successfully.");
    }

    private void deleteScheduler() {
        System.out.print("Enter bill name: ");
        String billName = scanner.nextLine();
        schedulerService.deleteSchedulerByBillName(user, billName);
        System.out.println("Scheduler deleted successfully.");
    }
}

