package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Bank;
import com.example.demo.model.PaymentTransaction;
import com.example.demo.model.User;
//import com.example.demo.model.PaymentTransaction;
import com.example.demo.service.PaymentTransactionService;
import com.example.demo.service.UserService;

/**
 * Unit and integration tests for the {@link PaymentTransactionService} class.
 * This class tests various methods of the {@link PaymentTransactionService} to
 * ensure that transaction operations such as retrieving, adding, updating,
 * and deleting transactions function as expected.
 * 
 * @author Kiranmoy Saha
 * @since 12th September, 2024
 * @owner KiranmoySaha
 */
@SpringBootTest
public class PaymentTransactionServiceTest {

	@Autowired
	PaymentTransactionService paymentTransactionService;
	
	@Autowired
	UserService userService;
	
    /**
     * Tests the {@link PaymentTransactionService#getAllTransactions()} method
     * to ensure that all transactions are retrieved correctly.
     */
	
	@Test
	public void testgetAll() {
		List<PaymentTransaction>list=paymentTransactionService.getAllTransactions();
		list.forEach((obj)->{
			System.out.println(obj.toString());
		});
	}
	
	 /**
     * Tests the {@link PaymentTransactionService#getTransactionsByUserId(int)} method
     * to ensure that transactions for a specific user ID are retrieved correctly.
     */
	
	@Test
	public void testGetByUserId() {
		List<PaymentTransaction>list=paymentTransactionService.getTransactionsByUserId(4);
		assertNotNull(list);
		assertEquals(list.size(),0);
		list.forEach((obj)->{
			System.out.println(obj.toString());
		});
	}
	
	 /**
     * Tests the {@link PaymentTransactionService#getTransactionById(int)} method
     * to ensure that a transaction is correctly retrieved by its ID.
     */ 
	
	@Test
	public void testGetTransactionById() {
		PaymentTransaction obj=paymentTransactionService.getTransactionById(1);
//		assertNotNull(obj);
//		assertEquals(obj.getTransactionId(), 1);
		
		obj=paymentTransactionService.getTransactionById(5);
		assertNull(obj);
//		assertEquals(obj.getTransactionId(), 1);
	}
	
	 /**
     * Tests the full lifecycle of a transaction, including adding, updating,
     * and deleting a transaction.
     */
	
	@Test
	public void testTransaction() {
		User user=userService.findByEmailAndPassword("bob.johnson@example.com","bob1");
		Bank bank=user.getBankDetails();
		LocalDate today = LocalDate.now();
        Date transactionDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
		PaymentTransaction obj=new PaymentTransaction(user,transactionDate,"rent",bank.getAccountNumber(),null,"debit",500.0,"ref1");
		paymentTransactionService.saveTransaction(obj);
		obj=paymentTransactionService.getTransactionById(1);
		assertNotNull(obj);
		obj.setReferenceNumber("reference123");
		paymentTransactionService.updateTransaction(obj);
		obj=paymentTransactionService.getTransactionById(1);
		assertEquals(obj.getReferenceNumber(),"reference123");
		paymentTransactionService.deleteTransaction(obj.getTransactionId());;
		obj=paymentTransactionService.getTransactionById(1);
		assertNull(obj);
		
	}
}