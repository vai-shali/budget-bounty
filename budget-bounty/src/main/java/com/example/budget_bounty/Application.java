/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.budget_bounty;

import java.util.*;
import java.text.*;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.budget_bounty.model.Bank;
import com.example.budget_bounty.model.Transaction;
import com.example.budget_bounty.model.User;


@SpringBootApplication
public class Application {

	static HashMap<String, User> users = new HashMap<>();
    static HashMap<String, Bank> bankDetailsMap = new HashMap<>();
//    static HashMap<String, Transaction> transactions = new HashMap<>();
    
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    	
    	users.put("abc@gmail.com", new User("abc", "abc@gmail.com", "9914458924", null, "12345"));
    	
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("WELCOME TO BUDGET BOUNTY!");
        while (choice != 4) {
            System.out.println("Select an option: ");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }
    
    static void registerUser(Scanner scanner)  //input validation
    {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your phone number:");
        String phone = scanner.nextLine();

        System.out.println("Enter your bank name:");
        String bankName = scanner.nextLine();
        System.out.println("Enter your account number:");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter your IFSC code:");
        String ifscCode = scanner.nextLine();
        System.out.println("Enter your initial balance:");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        Bank bankDetails = new Bank(bankName, accountNumber, ifscCode, balance, email);
        User user = new User(name, email, phone, bankDetails, password);
        
        System.out.println("\nPlease verify your details:");
        System.out.println(user.toString());
        System.out.println("Proceed with registration? (yes/no)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            if (users.containsKey(email)) {
                System.out.println("User already exists.");
            } else {
                users.put(email, user);
                bankDetailsMap.put(email, bankDetails);
                System.out.println("Registration successful.");
            }
        } else {
            System.out.println("Registration cancelled.");
        }
    }
    
    
    static void loginUser(Scanner scanner) 
    {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (!users.containsKey(email)) {
            System.out.println("Invalid email or password.");
        }
        else 
        {
            User user = users.get(email);
            if (user.getPassword().equals(password)) {
                System.out.println("Login successful.");
                System.out.println("Hello " + user.getUsername() + ", How can I help you today?");
                showMenu(scanner, user);
            } else {
                System.out.println("Invalid email or password.");
            }
        }
    }
    
    static void showMenu(Scanner scanner, User user) {
        int option = 0;
        while (option != 5) {
            System.out.println("\nMenu:");
            System.out.println("1. Link Bank or UPI");
            System.out.println("2. View Transactions");
            System.out.println("3. Schedule Bill");
            System.out.println("4. Make Payment");
            System.out.println("5. Logout");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    linkBankOrUPI(scanner, user);
                    break;
                case 2:
                    viewPastTransactions(user);
                    break;
                case 3:
                    scheduleBill(scanner, user);
                    break;
                case 4:
                    makePayment(scanner, user);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    static void linkBankOrUPI(Scanner scanner, User user) {
        System.out.println("Enter your bank name:");
        String bankName = scanner.nextLine();
        System.out.println("Enter your account number:");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter your IFSC code:");
        String ifscCode = scanner.nextLine();
        System.out.println("Do you want to link a UPI ID? (yes/no)");
        String linkUPI = scanner.nextLine();
        
        double balance = (user.getBankDetails() != null) ? user.getBankDetails().getBalance() : 0.0; //initial bank balance??
        
        Bank bankDetails = new Bank(bankName, accountNumber, ifscCode, balance, user.getEmail());

        if (linkUPI.equalsIgnoreCase("yes")) {
            System.out.println("Enter your UPI ID (format: abcd@ok<bankname>bank):");
            String upiId = scanner.nextLine();
            bankDetails.setUpiId(upiId);
        }
      
        user.setBankDetails(bankDetails);
        System.out.println(user.getBankDetails());
//        user.getBankDetails().setBalance(bankDetails.getBalance());
        user.getBankDetails().setBalance(1000);
        user.getBankDetails().setBalance(bankDetails.getBalance());
        bankDetailsMap.put(user.getEmail(), bankDetails);
        System.out.println("Bank/UPI linked successfully.");
        showMenu(scanner, user);
    }
    
    static void viewPastTransactions(User user) { //differentiate between past and scheduled transaction
        System.out.println("Transactions:");
        for (Transaction transaction : user.getTransactions()) {
            System.out.println(transaction.toString());
            System.out.println("----------------------------");
        }
    }

    static void scheduleBill(Scanner scanner, User user) { //functionality for recurring bills
    	String email = user.getEmail();
    	if(!bankDetailsMap.containsKey(email))
    	{
    		System.out.println("Bank Account not linked!!");
    		System.out.println("Link Bank Account? (y/n)");
    		String choice = scanner.nextLine();
    		if(choice.equalsIgnoreCase("y"))
    			linkBankOrUPI(scanner, user);
    	}
        System.out.println("Enter the transaction amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the transaction date (dd/MM/yyyy):");
        String dateString = scanner.nextLine();

        try {
            Date transactionDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(transactionDate);
            calendar.add(Calendar.DAY_OF_MONTH, -2);
            Date alertDate = calendar.getTime();

            String referenceNumber = generateReferenceNumber();
            
            System.out.println("Enter a description for the payment (e.g., 'Electricity Bill', 'Grocery Shopping'):");
            String paymentDescription = scanner.nextLine();
            
            Transaction transaction = new Transaction(user.getUsername(), paymentDescription+" (Scheduled Bill)", amount, transactionDate, referenceNumber);
            user.addTransaction(transaction);

            System.out.println("Bill scheduled successfully.");
            System.out.println("An alert will be sent on: " + new SimpleDateFormat("dd/MM/yyyy").format(alertDate));
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }
    
    private static String generateReferenceNumber() {
   	 String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder referenceNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            int index = random.nextInt(characters.length());
            referenceNumber.append(characters.charAt(index));
        }

        return referenceNumber.toString();
   }

	static void makePayment(Scanner scanner, User user) {
		
		String email = user.getEmail();
    	if(!bankDetailsMap.containsKey(email))
    	{
    		System.out.println("Bank Account not linked!!");
    		System.out.println("Link Bank Account? (y/n)");
    		String choice = scanner.nextLine();
    		if(choice.equalsIgnoreCase("y"))
    			linkBankOrUPI(scanner, user);
    	}
       System.out.println("Choose a payment method:");
       System.out.println("1. Internet Banking");
       System.out.println("2. UPI");
       int choice = scanner.nextInt();
       scanner.nextLine();
       
       if(choice == 1) {
    	   System.out.println("Enter the receiver's account number");
           long accNumber = scanner.nextLong();   
       }
       
       else if(choice == 2) {
    	   System.out.println("Enter the receiver's UPI id");
           String upiId = scanner.nextLine();
       }
       
       else
       {
    	   System.out.println("Invalid choice!");
    	   makePayment(scanner, user);
       }

       System.out.println("Enter the transaction amount:");
       double amount = scanner.nextDouble();
       
       System.out.println("Enter a description for the payment (e.g., 'Electricity Bill', 'Grocery Shopping'):");
       String paymentDescription = scanner.nextLine();
       
       scanner.nextLine();
       if(user.getBankDetails().getBalance() > amount)
       {
    	   double updatedBalance = user.getBankDetails().getBalance() - amount;
    	    
    	    // Set the new balance
    	   user.getBankDetails().setBalance(updatedBalance);
    	   
    	   String referenceNumber = generateReferenceNumber();
    	   
    	   LocalDate today = LocalDate.now();
           
           // Convert LocalDate to Date
           Date transactionDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

    	   Transaction transaction = new Transaction(user.getUsername(), paymentDescription, amount, transactionDate, referenceNumber);
           user.addTransaction(transaction);
           
    	   System.out.println("Payment Successful!");
       }
       else {
    	   System.out.println("Payment Failed! Not enough Balance!");
       }
       System.out.println("Remaining Bank balance: "+user.getBankDetails().getBalance());
	}


}