package com.example.budget_bounty.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.budget_bounty.exception.InvalidPaymentException;
/**
 * @author Vaishali Ganesh Kumar
 * @since 19th Aug,2024.
 */
public class UserTest {

    private User user;
    private Bank bank;
    private Transaction transaction;
    private Bill bill;
    private PaymentScheduler scheduler;
    /**
     * Sets up testing env
     */
    @BeforeEach
    public void setUp() {
        bank = new Bank("BankName", "AccountNumber" ,"IFSC", 1000, "x@gmail.com");
        user = new User(10, "john_doe", "john.doe@example.com", "123-456-7890", bank, "password123");
        transaction = new Transaction("John Doe", "Jane Doe", 1000.0, new Date(), "REF123");
        bill = new Bill(1, "Credit Card", 150.0, 15.0, new Date(), 1);
        scheduler = new PaymentScheduler();
    }
    /**
     * Checks User Initilizing
     */
    @Test
    public void testUserInitialization() {
        assertEquals("john_doe", user.getUsername());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("123-456-7890", user.getPhone());
        assertEquals(bank, user.getBankDetails());
        assertEquals("password123", user.getPassword());
    }
    /**
     * Testing of adding new Transaction.
     */
    @Test
    public void testAddTransaction() {
        user.addTransaction(transaction);
        assertTrue(user.getTransactions().contains(transaction));
    }

//    @Test
//    public void testSchedulePayment() {
//        user.schedulePayment(bill, "2024-09-01", scheduler);
//        // Verify if the bill was added to the scheduler's list of bills
//        // Assuming PaymentScheduler has a method to get the list of bills
//        assertTrue(scheduler.getBills().contains(bill));
//    }
    /**
     * Testing a immediate payment.
     */
    @Test
    public void testMakePayment() { //positive test case
        // This will only print the message;
    	try {
    		user.makePayment(bill);
    	} catch(InvalidPaymentException e) {
    		fail("Exception was not expected: "+e.getMessage());
    	}
    }
    /**
     * Testing the details of a user.
     */
    @Test
    public void testUserToString() {
        String expectedString = "UserName: john_doe\nEmail: john.doe@example.com\nPhone: 123-456-7890\n" + bank.toString();
        assertEquals(expectedString, user.toString());
    }
    /**
     * Test Adverse Conditions
     */
    @Test
    public void testUserWithNullBankDetails() {
        User userWithNullBank = new User(10, "john_doe", "john.doe@example.com", "123-456-7890", null, "password123");
        assertNull(userWithNullBank.getBankDetails());
    }
    /**
     * Test Adverse Conditions
     */
    @Test
    public void testEmptyTransactionsList() {
        User userWithEmptyTransactions = new User(10, "john_doe", "john.doe@example.com", "123-456-7890", bank, "password123");
        assertTrue(userWithEmptyTransactions.getTransactions().isEmpty());
    }
    /**
     * Test Adverse Conditions
     */
    @Test
    public void testAddNullTransaction() {
        user.addTransaction(null);
        assertTrue(user.getTransactions().isEmpty()); // Assuming null transactions should not be added
    }
    /**
     * test Adverse Conditions
     */
    @Test
    public void testSchedulePaymentWithInvalidDate() {
        user.schedulePayment(bill, "invalid-date", scheduler);
        // Verify if the scheduler has not added the bill, or handle as per your application's requirement
        assertFalse(scheduler.getBills().contains(bill)); // Assuming invalid dates result in no scheduling
    }
    /**
     * Test Adverse Conditions.
     */
    @Test
    public void testMakePaymentWithZeroAmount() { //positive test case
        try {
        	Bill zeroAmountBill = new Bill(2, "Credit Card", 0.0, 0.0, new Date(), 1);
        	user.makePayment(zeroAmountBill);
        	fail("Expected invalid payment exception to be thrown");
        } catch (InvalidPaymentException e) {
        	assertEquals("Invalid payment amount!!", e.getMessage());
        } catch (IllegalArgumentException e) {
        	assertEquals("Amount cannot be zero or negative!", e.getMessage());
        }
      
        // Verify if the system handles zero amount payments correctly
    }
    /**
     * Test Adverse Conditions
     */
    @Test
    public void testMakePaymentWithNegativeAmount() { //positive test case
        try {
            Bill negativeAmountBill = new Bill(3, "Credit Card", -150.0, 15.0, new Date(), 1);
        	user.makePayment(negativeAmountBill);
        	fail("Expected invalid payment exception to be thrown");
        } catch (InvalidPaymentException e) {
        	assertEquals("Invalid payment amount!!", e.getMessage());
        } catch (IllegalArgumentException e) {
        	assertEquals("Amount cannot be zero or negative!", e.getMessage());
        }
        // Verify if the system handles negative amounts correctly
    }
    /**
     * Test Adverse Conditions
     */
    @Test
    public void testUserToStringWithNullValues() {
    	User userWithNullValues = new User(10, null, null, null, null, null);
        String expectedString = "UserName: null\nEmail: null\nPhone: null\nNo Bank details to display";
        assertEquals(expectedString, userWithNullValues.toString());
    }


}
