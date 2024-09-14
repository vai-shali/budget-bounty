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

//import com.example.demo.model.PaymentTransaction;
import com.example.demo.model.Scheduler;
import com.example.demo.model.User;
import com.example.demo.service.SchedulerService;
import com.example.demo.service.UserService;


/**
 * Unit and integration tests for the {@link SchedulerService} class.
 * This class tests various methods of the {@link SchedulerService} to ensure 
 * that scheduled payment operations such as retrieving, adding, updating, 
 * and deleting scheduled payments function as expected.
 * 
 * @author Kiranmoy Saha
 * @since 2nd September, 2024
 */

@SpringBootTest
public class SchedulerTest {
	
	@Autowired
	SchedulerService schedulerService;
	
	@Autowired
	UserService userService;
	
	  /**
     * Tests the {@link SchedulerService#getAllScheduledPayments()} method
     * to ensure that all scheduled payments are retrieved correctly.
     */
	
	@Test
	public void testgetAll() {
		List<Scheduler>list=schedulerService.getAllScheduledPayments();
		assertNotNull(list);
		list.forEach((obj)->{
			System.out.println(obj.toString());
		});
	}
	
	  /**
     * Tests the {@link SchedulerService#getSchedulerById(int)} method to ensure 
     * that a scheduler is correctly retrieved by its ID.
     * 
     */
	
	@Test
	public void testGetById() {
		Scheduler obj=schedulerService.getSchedulerById(2);
		assertNotNull(obj);
		assertEquals(obj.getSchedulerId(), 2);
		
		obj=schedulerService.getSchedulerById(5);
		assertNull(obj);
	}
	
	   /**
     * Tests the {@link SchedulerService#getSchedulersByUserId(int)} method to ensure 
     * that scheduled payments for a specific user ID are retrieved correctly.
     * 
     */
	
	@Test
	public void testGetByUserId() {
		List<Scheduler>list=schedulerService.getSchedulersByUserId(2);
		assertNotNull(list);
		list.forEach((obj)->{
			System.out.println(obj.toString());
		});
	}
	
	   /**
     * Tests the {@link SchedulerService#getSchedulersByBillName(User, String)} method
     * to ensure that scheduled payments for a specific bill name are retrieved correctly.
     * 
     */
	
	@Test
	public void testGetByBillName() {
		User user=userService.getUserById(4);
		List<Scheduler>list=schedulerService.getSchedulersByBillName(user,"Monthly Rent Payment");
		assertNotNull(list);
		list.forEach((obj)->{
			System.out.println(obj.toString());
		});
		
		list=schedulerService.getSchedulersByBillName(user,"Rent Payment");
		assertEquals(list.size(),0);
	}
	
    /**
     * Tests the full lifecycle of a scheduled payment, including adding, updating,
     * and deleting a scheduled payment by bill name.
     */
	
	@Test
	public void testTransaction() {
		User user=userService.getUserById(4);
		LocalDate today = LocalDate.now();
        Date transactionDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Scheduler sch=new Scheduler(user,"rent",null,"Monthly rent",null,500.50,transactionDate,transactionDate,1,"monthly",10,null,0);
		schedulerService.addScheduler(sch, user);
		
		sch.setBillType("Gas");
		schedulerService.updateScheduler(sch, user);
		
		schedulerService.deleteSchedulerByBillName(user, "Monthly rent");
	}
}