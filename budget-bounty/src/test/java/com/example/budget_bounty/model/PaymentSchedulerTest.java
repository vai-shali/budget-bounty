package com.example.budget_bounty.model;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.budget_bounty.exception.InvalidPaymentException;
/**
 * @author Vishal Lodha
 * @since 19th Aug,2024.
 */
public class PaymentSchedulerTest {
	
	private PaymentScheduler scheduler;
	private User mockUser;
	/**
	 * Set up testing env.
	 */
	@BeforeEach
	public void setup() {
		scheduler = new PaymentScheduler();
	}
	/**
	 * Check if a new Bill is added into the Scheduler.
	 */
	@Test
	public void testAddBill() {  //positive testcase
	    Bill bill = new Bill(1, "Credit Card", 150.0, 15.0, new Date(), 1);

	    scheduler.addBill(bill);

	    // Verify that the bill was added to the scheduledBills list
	    assertTrue(scheduler.getBills().contains(bill));
	}
	/**
	 * Tests Descheduling of a bill.
	 */
	@Test
	public void testRemoveBill() { //positive testcase
	    Bill bill = new Bill(1, "Credit Card", 150.0, 15.0, new Date(), 1);

	    scheduler.addBill(bill);
	    scheduler.removeBill(bill);

	    // Verify that the bill was removed from the scheduledBills list
	    assertFalse(scheduler.getBills().contains(bill));
	}
	/**
	 * Checks Adverse Conditions
	 */
	@Test
    public void testAddBillThrowsExceptionForNullBill() { //negative test case
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        	scheduler.addBill(null);
        });
        assertEquals("Bill cannot be null", exception.getMessage());
    }
	/**
	 * Checks Adverse Conditions
	 */
	@Test
	public void testRemoveBillNotInList() { //negative test case
		Bill bill = new Bill(1, "Credit Card", 150.0, 15.0, new Date(), 1);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			scheduler.removeBill(bill);
		});
		assertEquals("Bill not found in the scheduled bills list",exception.getMessage());
	}
	/**
	 * Checks Adverse Conditions.
	 */
	@Test
	public void testProcessPaymentWithNullUser() { //negative test case
		Bill bill = new Bill(1, "Credit Card", 150.0, 15.0, new Date(), 1);
		scheduler.addBill(bill);
		assertThrows(InvalidPaymentException.class, () -> {
	        scheduler.processPayments(null);
	    });
	}
	/**
	 * Tests and overdue bill with scheduler
	 * @throws InvalidPaymentException
	 */
	@Test
    public void testProcessPaymentsWithOverdueBill() throws InvalidPaymentException {
        
		mockUser = mock(User.class);

        // Set up an overdue bill (due date is in the past)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date overdueDate;
        try {
            overdueDate = dateFormat.parse("01/01/2024"); // Past date
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Bill overdueBill = new Bill(999, "Credit Card", 150.0, 15.0, overdueDate, 1, false);

        // Add the overdue bill to the scheduler
        scheduler.addBill(overdueBill);
		
		// Call processPayments with the mock user
		scheduler.processPayments(mockUser);

        // Verify that makePayment was not called on the mockUser
        verify(mockUser, never()).makePayment(overdueBill);
    }

}
