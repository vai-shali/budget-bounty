package hiber.hiber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hiber.hiber.model.Bank;
import hiber.hiber.model.PaymentTransaction;
import hiber.hiber.model.Scheduler;
import hiber.hiber.model.User;
import hiber.hiber.service.BankService;
import hiber.hiber.service.PaymentTransactionService;
import hiber.hiber.service.SchedulerService;
import hiber.hiber.service.UserService;
import hiber.hiber.util.HibernateUtil;

public class Main {
	public static void bankCheck() {
		BankService bs=new BankService();
		UserService us=new UserService();
		User uobj=us.getUserById(1);
		Bank obj=new Bank(6,"Standard","IFSC0006","1234321","eve@okemp.com","Savings",5000.0,uobj);
		List<Bank> list=bs.getAllBanks();
		System.out.println("Before Insert");
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		bs.addBank(obj);

		System.out.println("After Insert");
		list=bs.getAllBanks();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		obj.setBalance(4500.0);
		bs.updateBank(obj);

		System.out.println("After Update");
		list=bs.getAllBanks();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		bs.deleteBank(6);

		System.out.println("After Delete");
		list=bs.getAllBanks();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
	}
	
	public static void userCheck() {
		UserService us=new UserService();
		User uobj=new User(6,"name","username","password","9876543210","email",0);
		List<User> list=us.getAllUsers();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		us.saveUser(uobj);
		User dobj=us.getUserById(6);
		System.out.println(dobj.toString());
		uobj.setRole(1);
		us.updateUser(uobj);
		list=us.getAllUsers();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		System.out.println(us.existsByEmail("email"));
		dobj=us.findByEmailAndPassword("email", "password");
		System.out.println(dobj.toString());
		us.deleteUser(6);
		list=us.getAllUsers();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
	}
	
	public static void transactionCheck() {
        PaymentTransactionService pts = new PaymentTransactionService();
        UserService us = new UserService();

        // Fetch a user by ID for associating with the transaction
        User user = us.getUserById(1);

        // Create a new PaymentTransaction
        PaymentTransaction pt = new PaymentTransaction(user, new Date(),"Payment for rent","fromAccountNumber","toAccountNumber","debit", 200.0,"referenceNumber5");

        // Insert the PaymentTransaction
        pts.saveTransaction(pt);

        // Retrieve and print all transactions before any updates
        System.out.println("Before Update:");
        List<PaymentTransaction> transactions = pts.getAllTransactions();
        transactions.forEach(transaction -> System.out.println(transaction.toString()));

        // Update the transaction
        pt.setAmount(250.0);
        pts.updateTransaction(pt);

        // Retrieve and print all transactions after the update
        System.out.println("After Update:");
        transactions = pts.getAllTransactions();
        transactions.forEach(transaction -> System.out.println(transaction.toString()));

        // Retrieve and print a transaction by ID
        PaymentTransaction retrievedTransaction = pts.getTransactionById(2);
        System.out.println("Retrieved by ID: " + retrievedTransaction.toString());

        // Delete the transaction by ID
        pts.deleteTransaction(2);

        // Retrieve and print all transactions after deletion
        System.out.println("After Deletion:");
        transactions = pts.getAllTransactions();
        transactions.forEach(transaction -> System.out.println(transaction.toString()));
        
        System.out.println("Transactions for USER ID 2:");
        transactions = pts.getTransactionsByUserId(2);
        transactions.forEach(transaction -> System.out.println(transaction.toString()));
    }
	
	private static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + e.getMessage());
            return null;
        }
    }
	
	public static void schedulerCheck() {
        SchedulerService ss = new SchedulerService();
        UserService us = new UserService();
        User user = us.getUserById(2); // Fetch a user to associate with Scheduler
        
        ss.deleteScheduler(5);
        Date dueDate = parseDate("03/10/2024");
        Date scheduledDate = parseDate("01/10/2024");
        
        // Create a new Scheduler
        Scheduler scheduler = new Scheduler(
            user,                        // Associated User
            "Electricity",               // Bill Type
            1002,                        // Customer ID (optional)
            "Electric Bill",             // Bill Name
            "2763723672263",           // Payee Account
            150.0,                       // Amount
            dueDate,                  // Due Date
            scheduledDate,                  // Scheduled Date
            1,                           // Recurring (true)
            "monthly",                   // Frequency
            5,                          // End After (number of payments)
            null,                        // End By Date (optional)
            0                            // Is Paid (false)
        );

        // Print all schedulers before any operations
        System.out.println("Before Insert:");
        List<Scheduler> schedulers = ss.getAllScheduledPayments();
        schedulers.forEach(System.out::println);

        // Add Scheduler
        ss.addScheduler(scheduler, user);

        // Print all schedulers after insertion
        System.out.println("After Insert:");
        schedulers = ss.getAllScheduledPayments();
        schedulers.forEach(System.out::println);

        // Update Scheduler
        scheduler.setAmount(200.0); // Update amount
        ss.updateScheduler(scheduler, user);

        // Print all schedulers after update
        System.out.println("After Update:");
        schedulers = ss.getAllScheduledPayments();
        schedulers.forEach(System.out::println);

        // Retrieve Scheduler by ID
        Scheduler retrievedScheduler = ss.getSchedulersByUserId(user.getUserId()).stream()
                .filter(s -> s.getSchedulerId() == 1)
                .findFirst()
                .orElse(null);
        System.out.println("Retrieved by ID: " + retrievedScheduler);

        // Retrieve schedulers by Bill Name
        List<Scheduler> schedulersByBillName = ss.getSchedulersByBillName(user, "Electric Bill");
        System.out.println("Schedulers for Bill Name 'Electric Bill':");
        schedulersByBillName.forEach(System.out::println);

        // Delete Scheduler
        ss.deleteScheduler(1);

        // Print all schedulers after deletion
        System.out.println("After Delete:");
        schedulers = ss.getAllScheduledPayments();
        schedulers.forEach(System.out::println);
        
        // Try deleting by Bill Name
        boolean isDeleted = ss.deleteSchedulerByBillName(user, "Electric Bill");
        System.out.println("Scheduler deletion by Bill Name successful: " + isDeleted);
    }
	
	public static void main(String[] args) {
		schedulerCheck();
		transactionCheck();
		bankCheck();
		userCheck();
	}
	
}
