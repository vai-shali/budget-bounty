package com.example.budget_bounty.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class TransactionTest {
	
	Transaction transaction;
	
	@Test
	public void testTransactionCreationAndToString() throws ParseException { //positive test case
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date transactionDate = dateFormat.parse("21/08/2024");
	    
	    Transaction transaction = new Transaction("Alice", "Bob", 100.0, transactionDate, "REF123");

	    // Expected format for toString
	    String expectedString = "Transaction From: Alice\nTransaction To: Bob\nAmount: 100.0\nDate: " + dateFormat.format(transactionDate) + "\nReference Number: REF123";
	    assertEquals(expectedString, transaction.toString());
	}

	@Test
	public void testTransactionWithNullValues() { //negative test case
	    Date transactionDate = new Date();
	    
	    assertThrows(IllegalArgumentException.class, ()->{
	    	new Transaction(null, null, 0.0, transactionDate, null);
	    });
	}


}
