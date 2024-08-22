package com.example.budget_bounty;
import com.example.budget_bounty.model.Bank;
import com.example.budget_bounty.model.User;
import com.example.budget_bounty.model.Transaction;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Vishal Lodha
 * @since 19th Aug,2024
 */
class ApplicationTest {
	static HashMap<String, User> users = new HashMap<>();
	/**
	 * Set up of testing env.
	 */
	@BeforeEach
    public void setUp() {
        // Initialize users map and add a test user
        Application.users = new HashMap<>();
        Application.users.put("abc@gmail.com", new User("abc", "abc@gmail.com", "9914458924", null, "12345"));
    }
    
	/**
	 * Registering User
	 */
	@Test
	//void test() {
		//fail("Not yet implemented");
		
	public void testRegisterUser() {
		String input = "John Doe\njohn@example.com\n1234567890\nBank A\n123456\nIFSC001\n1000.0\npassword123\nyes\n";
        Scanner scanner = new Scanner(input);
        Application.registerUser(scanner);
        
        assertTrue(Application.users.containsKey("john@example.com")); //checks hashmap if user details are stored
		
	}
	/**
	 * Test user Login
	 */
	@Test
	public void testLoginUser() {
		//String input = "John Doe\njohn@example.com\n1234567890\nBank A\n123456\nIFSC001\n1000.0\npassword123\nyes\n";
        //Scanner scanner = new Scanner(input);
        //Application.registerUser(scanner); //checks if user registered already
        
		String loginInput = "\nabc@gmail.com\n12345\n";
        Scanner loginScanner = new Scanner(loginInput);

        // Perform the login operation
        Application.loginUser(loginScanner);

        // Verify the user details after login attempt
        User loggedInUser = Application.users.get("abc@gmail.com");
        assertNotNull(loggedInUser, "User should be present in the map after login");
        assertEquals("12345", loggedInUser.getPassword(), "Password should match");
        assertEquals("abc", loggedInUser.getUsername(), "Username should match");
		
	}
	
//	@Test
//	public void testInvalidLogin() {
//		String loginInput = "invalid@example.com\nwrongpassword\n";
//        Scanner loginScanner = new Scanner(loginInput);
//        Application.loginUser(loginScanner);
//        
//        assertFalse(Application.users.containsKey("invalid@example.com"));
//	}
	
	@Test
	public void testShowMenu() {
		
	}
	/**
	 * testLinkingBankOrUpi
	 */
	@Test
	public void testLinkBankorUPI() {
		Bank initialBank = new Bank("Initial Bank", "0000000000", "IFSC000", 1000.0, "john@example.com");
	    User user = new User("john_doe", "john@example.com", "1234567890", initialBank, "password123");

	    // Provide the necessary input for the linkBankOrUPI method
	    String input = "Bank A\n1234567890\nIFSC001\nno\n5\n";
	    Scanner scanner = new Scanner(input);

	    // Call the linkBankOrUPI method with the provided Scanner
	    try {
	    	Application.linkBankOrUPI(scanner, user);
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    	}

	    // Retrieve the updated bank details from the user
	    Bank linkedBank = user.getBankDetails();

	    // Assertions to verify that the bank details were updated correctly
	    assertNotNull(linkedBank, "Bank details should not be null");
//	    assertEquals("Bank A", linkedBank.getBankName(), "Bank name should be 'Bank A'");
//	    assertEquals("1234567890", linkedBank.getAccountNumber(), "Account number should be '1234567890'");
//	    assertEquals("IFSC001", linkedBank.getIfscCode(), "IFSC code should be 'IFSC001'");
//	    assertEquals("abcd@okbanknamebank", linkedBank.getUpiId(), "UPI ID should be 'abcd@okbanknamebank'");
	    assertEquals(1000.0, linkedBank.getBalance(), "Balance should be 1000.0");

	    System.out.println("Test for linking bank and UPI passed.");
		
	}
	/**
	 * Test Viewing Past Transaction.
	 */
	@Test
	public void testViewPastTransactions() {
		// Setup bank details
	    Bank bank = new Bank("Bank A", "123456", "IFSC001", 1000.0, "john@example.com");
	    User user = new User("john_doe", "john@example.com", "1234567890", bank, "password123");

	    // Add a transaction
	    Date now = new Date();
	    Transaction transaction = new Transaction("john_doe", "Payment Description", 100.0, now, "REF123");
	    user.addTransaction(transaction);

	    
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    PrintStream originalOut = System.out;
	    System.setOut(new PrintStream(outContent));

	    // Call the method
	    Application.viewPastTransactions(user);

	    // Restore original System.out
	    System.setOut(originalOut);

	    // Check if the output contains the transaction details
	    String output = outContent.toString();
	    assertTrue(output.contains("Transaction From: john_doe"));
	    assertTrue(output.contains("Transaction To: Payment Description"));
	    assertTrue(output.contains("Amount: 100.0"));
	    assertTrue(output.contains("Reference Number: REF123"));

	    System.out.println("Test for viewing past transactions passed.");
	}
	/**
	 * Test Scheduling Payments.
	 */
	@Test
	public void testScheduleBill() {
		String input = "200.0\n01/09/2023\nRent Payment\n";
        
	        
	 
		
	}
	
	

}
