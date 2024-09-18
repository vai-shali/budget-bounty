//package com.example.demo.ConsoleHandler;
//
//import com.example.demo.model.Bank;
//import com.example.demo.model.PaymentTransaction;
//import com.example.demo.model.User;
//import com.example.demo.service.BankService;
//import com.example.demo.service.PaymentTransactionService;
//import com.example.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//
//@Component
//public class MainMenu {
//
//    private static UserService userService;
//    private static BankService bankService;
//    private static PaymentTransactionService transactionService;
//    private static SchedulerMenu schedulerMenu;
//
//    @Autowired
//    public MainMenu(UserService userService, BankService bankService, PaymentTransactionService transactionService,SchedulerMenu schedulerMenu) {
//        MainMenu.userService = userService;
//        MainMenu.bankService = bankService;
//        MainMenu.transactionService = transactionService;
//        MainMenu.schedulerMenu = schedulerMenu;
//    }
//
//
//    public static void mainMenu(){
//        Scanner scanner = new Scanner(System.in);
//        int choice = 0;
//        System.out.println("WELCOME TO BUDGET BOUNTY!");
//        while (choice != 4) {
//            // 1. Registering New User
//            // 2. Login current User
//            // 3. Quit.
//            System.out.println("Select an option: ");
//            System.out.println("1. Register");
//            System.out.println("2. Login");
//            System.out.println("3. Quit");
//            choice = scanner.nextInt();
//            scanner.nextLine();
//            switch (choice) {
//                case 1:
//                    registerUser(scanner);
//                    break;
//                case 2:
//                    loginUser(scanner);
//                    break;
//                case 3:
//                    System.out.println("Goodbye!");
//                    System.exit(0);
//                    break;
//                default:
//                    System.out.println("Invalid choice");
//            }
//        }
//        scanner.close();
//    }
//    /**
//     * Registering a new User
//     *
//     * @param scanner
//     */
//    static void registerUser(Scanner scanner) // input validation
//    {
//        System.out.println("Enter your name:");
//        String name = scanner.nextLine();
//        System.out.println("Enter your username:");
//        String username = scanner.nextLine();
//        System.out.println("Enter your password:");
//        String password = scanner.nextLine();
//        System.out.println("Enter your phone number:");
//        String phone = scanner.nextLine();
//        System.out.println("Enter your email:");
//        String email = scanner.nextLine();
//        User user = new User(name, username, password, phone, email, 0);
//
//        System.out.println("\nPlease verify your details:");
//        System.out.println(user.toString());
//        System.out.println("Proceed with registration? (yes/no)");
//        String confirmation = scanner.nextLine();
//
//        if (confirmation.equalsIgnoreCase("yes")) {
//            if (userService.existsByEmail(email)) {
//                System.out.println("User already exists.");
//            } else {
//                userService.saveUser(user);
//                System.out.println("Registration successful.");
//                user = userService.findByEmailAndPassword(email, password);
//                showMenu(scanner, user);
//            }
//        } else {
//            System.out.println("Registration cancelled.");
//        }
//    }
//
//    /**
//     * Logging in a user and checking the validity of the user.
//     *
//     * @param scanner
//     */
//    static void loginUser(Scanner scanner) {
//        System.out.println("Enter your email:");
//        String email = scanner.nextLine();
//        System.out.println("Enter your password:");
//        String password = scanner.nextLine();
//
//        if (!userService.existsByEmail(email)) {
//            System.out.println("Invalid email.");
//        } else {
//            User user = userService.findByEmailAndPassword(email, password);
//            if (user!=null) {
//                System.out.println("Login successful.");
//                System.out.println("Hello " + user.getUsername() + ", How can I help you today?");
//                showMenu(scanner, user);
//            } else {
//                System.out.println("Invalid email or password.");
//            }
//        }
//    }
//
//    /**
//     * Actions for a user.
//     *
//     * @param scanner
//     * @param user
//     */
//    static void showMenu(Scanner scanner, User user) {
//        int option = 0;
//        while (option != 5) {
//            System.out.println("\nMenu:");
//            System.out.println("1. Link Bank or UPI");
//            System.out.println("2. View Transactions");
//            System.out.println("3. Payment Scheduler Menu");
//            System.out.println("4. Make Payment");
//            System.out.println("5. Logout");
//            option = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (option) {
//                case 1:
//                    linkBankOrUPI(scanner, user);
//                    break;
//                case 2:
//                    viewPastTransactions(scanner, user);
//                    break;
//                case 3:
//                    scheduleBill(scanner, user);
//                    break;
//                case 4:
//                    makePayment(scanner, user);
//                    break;
//                case 5:
//                    System.out.println("Logging out...");
//                    mainMenu();
//                default:
//                    System.out.println("Invalid option. Please try again.");
//            }
//        }
//    }
//
//    /**
//     * Linking UPI
//     *
//     * @param scanner
//     * @param user
//     */
//    static void linkBankOrUPI(Scanner scanner, User user) {
//        if(user.getBankDetails() != null)
//        {
//            System.out.println("Your bank details are linked already!");
//            showMenu(scanner, user);
//        }
//        else {
//            System.out.println("Enter your bank name:");
//            String bankName = scanner.nextLine();
//            System.out.println("Enter your account number:");
//            String accountNumber = scanner.nextLine();
//            System.out.println("Enter your IFSC code:");
//            String ifscCode = scanner.nextLine();
//            System.out.println("Enter your account type:");
//            String accountType = scanner.nextLine();
//            System.out.println("Do you want to link a UPI ID? (yes/no)");
//            String linkUPI = scanner.nextLine();
//
//            //        Bank(int bankId, String bankName, String ifsc, String accountNumber, String upiId, String accountType, double balance, User userObj)
//
//            String upiId=null;
//            if (linkUPI.equalsIgnoreCase("yes")) {
//                System.out.println("Enter your UPI ID (format: abcd@ok<bankname>bank):");
//                upiId = scanner.nextLine();
//            }
//
//            Bank bankDetails = new Bank(bankName, ifscCode, accountNumber, upiId, accountType, 10000, user);
//
//            user.setBankDetails(bankDetails);
//            bankService.addBank(bankDetails);
//            System.out.println("Bank/UPI linked successfully.");
//            showMenu(scanner, user);
//        }
//    }
//
//    /**
//     * View Past transactions for a user
//     *
//     * @param user
//     */
//    static void viewPastTransactions(Scanner scanner, User user) {
//        System.out.println("Transactions:");
//        System.out.println(user.getUserId());
//        List<PaymentTransaction> transactions = transactionService.getTransactionsByUserId(user.getUserId());
//        if(transactions.size() ==0)
//        {
//            System.out.println("No Past Transactions to View");
//        }
//        else {
//            for (PaymentTransaction transaction : transactions) {
//                System.out.println(transaction.toString());
//                System.out.println("----------------------------");
//            }
//        }
//        showMenu(scanner, user);
//    }
//
//    /**
//     * Scheduling a Bill
//     *
//     * @param scanner
//     * @param user
//     */
//    static void scheduleBill(Scanner scanner, User user) {
//        if (user.getBankDetails() == null) {
//            System.out.println("Bank Account not linked!!");
//            System.out.println("Link Bank Account? (y/n)");
//            String choice = scanner.nextLine();
//            if (choice.equalsIgnoreCase("y"))
//                linkBankOrUPI(scanner, user);
//        }
//        else {
//            schedulerMenu.handleUserInput(user);
//        }
//        showMenu(scanner, user);
//    }
//
//    /**
//     * Generating a reference number for a transaction.
//     *
//     * @return String
//     */
//    private static String generateReferenceNumber() {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        StringBuilder referenceNumber = new StringBuilder();
//        Random random = new Random();
//
//        for (int i = 0; i < 15; i++) {
//            int index = random.nextInt(characters.length());
//            referenceNumber.append(characters.charAt(index));
//        }
//
//        return referenceNumber.toString();
//    }
//
//    /**
//     * Make an immediate Payment.
//     *
//     * @param scanner
//     * @param user
//     */
//    static void makePayment(Scanner scanner, User user) {
//
//        if (user.getBankDetails() == null) {
//            System.out.println("Bank Account not linked!!");
//            System.out.println("Link Bank Account? (y/n)");
//            String choice = scanner.nextLine();
//            if (choice.equalsIgnoreCase("y"))
//                linkBankOrUPI(scanner, user);
//        }
//
//        System.out.println("Choose a payment method:");
//        System.out.println("1. Internet Banking");
//        System.out.println("2. UPI");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//        String payeeAcc = null;
//
//        if (choice == 1) {
//            System.out.println("Enter the receiver's account number");
//            payeeAcc = scanner.nextLine();
//        } else if (choice == 2) {
//            System.out.println("Enter the receiver's UPI id");
//            payeeAcc = scanner.nextLine();
//        } else {
//            System.out.println("Invalid choice!");
//            makePayment(scanner, user);
//        }
//
//        System.out.println("Enter the transaction amount:");
//        Double amount = scanner.nextDouble();
//        scanner.nextLine();
//
//        System.out.println("Enter a description for the payment (e.g., 'Electricity Bill', 'Grocery Shopping'):");
//        String paymentDescription = scanner.nextLine();
//
//        if (user.getBankDetails().getBalance() > amount) {
//            double updatedBalance = user.getBankDetails().getBalance() - amount;
//            user.getBankDetails().setBalance(updatedBalance);
//            bankService.updateBank(user.getBankDetails());
//
//            String referenceNumber = generateReferenceNumber();
//
//            LocalDate today = LocalDate.now();
//            Date transactionDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//            try {
////            	public PaymentTransaction(User user, Date transactionDate, String transactionName,String fromAccountNumber, String toAccountNumber, String transactionType,Double amount, String referenceNumber) {
//                PaymentTransaction newTransaction = new PaymentTransaction(user, transactionDate, paymentDescription, user.getBankDetails().getAccountNumber(), payeeAcc, "debited", amount, referenceNumber);
//                transactionService.saveTransaction(newTransaction);
//                System.out.println("Payment Successful!");
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//
//        } else {
//            System.out.println("Payment Failed! Not enough Balance!");
//        }
//        System.out.println("Remaining Bank balance: " + user.getBankDetails().getBalance());
//
//
//    }
//}
