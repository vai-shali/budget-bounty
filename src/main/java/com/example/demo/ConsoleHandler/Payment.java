package com.example.demo.ConsoleHandler;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.PaymentService;

@Component
public class Payment {
	private static PaymentService paymentService;

    @Autowired
    public Payment(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public static void processPayment() {
    	Scanner sc=new Scanner(System.in);
    	System.out.println(LocalDate.now());
    	System.out.println("PAYMENT TEST");
//    	paymentService.makePayment();
//    	System.out.println("make payment?(0/1)");
//    	int n=sc.nextInt();
//    	if(n==1)
//    		paymentService.makePayment(); // Call the makePayment method here
//    	else
//    		System.out.println("payment paused");
    	
    	System.out.println("REMINDER TEST");
//    	paymentService.addReminder();
//    	System.out.println("make reminders?(0/1)");
//    	int n1=sc.nextInt();
//    	if(n1==1)
//    		paymentService.addReminder();
//    	else
//    		System.out.println("reminder paused");
    }
}
