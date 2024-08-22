package com.example.budget_bounty.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author Vaishali Ganesh Kumar
 * @since 19th Aug,2024
 */
public class BillTest {
	private Bill bill;
	/**
	 * Set up for each test.
	 * @throws ParseException
	 */
	@BeforeEach
	public void setup() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("22/08/2024");
        bill = new Bill(1, "Credit Card", 150.0, 15.0, date, 1);

	}
	/**
	 * Test Bill Status.
	 * @throws ParseException
	 */
	@Test
	public void testGetBillStatus() throws ParseException{ //positive test case
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Date overdueDate = dateFormat.parse("20/08/2024");
		Date pendingDate = dateFormat.parse("28/08/2024");
		
		Bill overdue = new Bill(1, "Credit Card", 150.0, 15.0, overdueDate, 1);
		Bill pending = new Bill(1, "Credit Card", 150.0, 15.0, pendingDate, 1);
		
		assertEquals("Overdue", overdue.getBillStatus());
		assertEquals("Pending", pending.getBillStatus());
		
		pending.setPaid(true);
		
		assertEquals("Paid", pending.getBillStatus());
		
	}
	/**
	 * Tests the Return Details of a Bill.S
	 */
	@Test
	public void testBillToString() { //positive test case
		
	    assertEquals("Bill{id=1, paymentMethod='Credit Card', amount=150.0, tax=15.0, totalAmount=165.0, dueDate=22/08/2024, userId=1}", bill.toString());

	}
	/**
	 * Test Adverse Conditions
	 */
	@Test
	public void testBillWithNullValues() { //negative test case
	    assertThrows(IllegalArgumentException.class, ()->{
	    	new Bill(1, null, 0.0, 0.0, null, 1, false);
	    });
	}
	/**
	 * Test Adverse Conditions
	 */
	@Test
	public void testBillWithNegativeAmount() { //negative test case
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()->{
			bill.setAmount(-15);
			});
	    assertEquals("Amount cannot be zero or negative!", thrown.getMessage());

	}
}
