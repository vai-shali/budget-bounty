package com.example.budget_bounty.exception;
/**
 * <h1> Class to raise a invalid Exception </h1>
 * @author Kiranmoy
 * @since 17th Aug,2024
 * 
 */
public class InvalidPaymentException extends Exception{
	/**
	 * Constructor to raise Exception
	 * @param msg
	 */
	public InvalidPaymentException(String msg) {
		super(msg);
	}
}
