package hiber.hiber.controller;

import java.sql.Date;
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
        List<Scheduler> schedulers = schedulerService.getSchedulersByUser(user);
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
        System.out.print("Enter bill type: ");
        String billType = scanner.nextLine();
        System.out.print("Enter customer ID (or leave empty if none): ");
        String customerIdStr = scanner.nextLine().trim();
        Integer customerId = customerIdStr.isEmpty() ? null : Integer.parseInt(customerIdStr);
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
        String endAfterStr = scanner.nextLine().trim();
        Integer endAfter = endAfterStr.isEmpty() ? null : Integer.parseInt(endAfterStr);
        System.out.print("Enter end by date (YYYY-MM-DD) or leave blank: ");
        String endByStr = scanner.nextLine();
        Date endBy = endByStr.isEmpty() ? null : Date.valueOf(endByStr);
        System.out.print("Is paid (1-true/0-false): ");
        Integer isPaid = scanner.nextInt();

        Scheduler scheduler = new Scheduler(user, billType, customerId, billName, payeeAcc, amount, dueDate, scheduledDate, isRecurring, frequency, endAfter, endBy, isPaid);
        schedulerService.addScheduler(scheduler, user);
        System.out.println("Scheduler added successfully.");
    }

    private void updateScheduler() {
        System.out.print("Enter scheduler ID: ");
        Integer schedulerId = scanner.nextInt();
        scanner.nextLine();
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

        Scheduler scheduler = new Scheduler(schedulerId, user, billType, customerId, billName, payeeAcc, amount, dueDate, scheduledDate, isRecurring, frequency, endAfter, endBy, isPaid);
        schedulerService.updateScheduler(scheduler, user);
        System.out.println("Scheduler updated successfully.");
    }

    private void deleteScheduler() {
        System.out.print("Enter bill name: ");
        String billName = scanner.nextLine();
        schedulerService.deleteSchedulerByBillName(user, billName);
        System.out.println("Scheduler deleted successfully.");
    }
}

