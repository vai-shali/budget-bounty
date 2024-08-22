package com.example.budget_bounty.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.budget_bounty.exception.InvalidPaymentException;
/**
 * @author Vaishali Ganesh Kumar
 */
public class AdminTest {
	Admin admin;
	Bill bill;
	/**
	 * Sets Testing Env
	 * @throws ParseException
	 */
	@BeforeEach
	public void setup() throws ParseException {
		admin = new Admin();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date date = dateFormat.parse("28/10/2024");
	    
		
	    bill = new Bill(1, "Credit Card", 150.0, 15.0, date, 1);
	}
	
	//positive cases
	/**
	 * Test Scheduling Bills
	 */
	@Test
	public void testScheduleBill() {

		assertFalse(admin.getPaymentScheduler().getBills().contains(bill));
	    
		admin.scheduleBill(bill);
	    assertTrue(admin.getPaymentScheduler().getBills().contains(bill));
	    
	    admin.unscheduleBill(bill);
		assertFalse(admin.getPaymentScheduler().getBills().contains(bill));

	}
	/**
	 * Tests the processing of a scheduled payment
	 * @throws InvalidPaymentException
	 */
	@Test
	public void testProcessPayments() throws InvalidPaymentException {
		User user = mock(User.class);
		admin.scheduleBill(bill);
		admin.processAllPayments(user);
		
		verify(user).makePayment(bill);
	}

	//negative cases
	/**
	 * Test adverse conditions
	 */
	@Test
	public void testScheduleNullBill() {
		assertThrows(IllegalArgumentException.class, ()->{
			admin.scheduleBill(null);
		});
	}
	/**
	 * Test adverse conditions
	 */
	@Test
	public void testUnscheduleNullBill() {
	    
	    assertThrows(IllegalArgumentException.class, () -> {
	        admin.unscheduleBill(null);
	    });
	}
	/**
	 * Test adverse conditions
	 */
	@Test
	public void testProcessPaymentsWithNullUser() {
	    
	    admin.scheduleBill(bill);

	    assertThrows(InvalidPaymentException.class, () -> {
	        admin.processAllPayments(null);
	    });
	}
	/**
	 * Test adverse conditions
	 * @throws InvalidPaymentException
	 */
	@Test
	public void testProcessPaymentsWithNoScheduledBills() throws InvalidPaymentException {
	    User user = mock(User.class);

	    admin.processAllPayments(user);

	    verify(user, never()).makePayment(any(Bill.class));
	}


}
