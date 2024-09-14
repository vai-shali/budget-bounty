package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AccessDeniedException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Scheduler;
import com.example.demo.model.User;
import com.example.demo.repository.SchedulerRepository;

/**
 * Service class for managing scheduled payments and bills.
 * Provides methods to perform CRUD operations on scheduled payments.
 * @author Vaishali
 * @since 2nd September, 2024
 */
@Service
public class SchedulerService {

	@Autowired
    private SchedulerRepository schedulerRepository;

    @Autowired
    private UserService userService;


    /**
     * Retrieves all scheduled payments (admin functionality).
     *
     * @return a list of all Scheduler objects
     */
    public List<Scheduler> getAllScheduledPayments(int userId) {
    	User existingUser = userService.getUserById(userId);
        
        if (existingUser != null && existingUser.getRole() == 1) {
            return schedulerRepository.findAll(); // if User is an admin, return all schedulers
        } else {
            throw new AccessDeniedException("Access Denied! You do not have permission to view all schedulers.");
        }
    }

    /**
     * Deletes a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill to be deleted
     */
    public void deleteScheduler(int schedulerId) {
    	if (!schedulerRepository.existsById(schedulerId)) {
            throw new IllegalArgumentException("Scheduler not found with ID: " + schedulerId);
        }
        schedulerRepository.deleteById(schedulerId);
    }

    /**
     * Retrieves a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill to retrieve
     * @return the Scheduler object with the specified ID, or null if not found
     */
    public Scheduler getSchedulerById(int schedulerId) {
        Optional<Scheduler> scheduler = schedulerRepository.findById(schedulerId);
        return scheduler.orElse(null);
    }

    /**
     * Retrieves all scheduled bills for a specific user by user ID.
     *
     * @param userId the ID of the user whose scheduled bills are to be retrieved
     * @return a list of Scheduler objects associated with the specified user ID
     */
    public List<Scheduler> getSchedulersByUserId(int userId) {
        return schedulerRepository.findByUserId(userId);
    }

    /**
     * Retrieves scheduled bills for a user by bill name.
     *
     * @param user the User object whose scheduled bills are to be retrieved
     * @param billName the name of the bill to search for
     * @return a list of Scheduler objects that match the given bill name for the specified user
     */
    public Scheduler getSchedulersByBillName(int userId, String billName) {
        return schedulerRepository.findByUserUserIdAndBillName(userId, billName);
    }

    /**
     * Adds a new scheduled bill.
     *
     * @param scheduler the Scheduler object to be added
     * @param user the User object associated with the scheduled bill
     */
    public void addScheduler(Scheduler scheduler, int userId) {
    	// Check if the user exists using UserService
    	User existingUser = userService.getUserById(userId);
        if (existingUser!=null) {
        	scheduler.setUser(existingUser);	// Associate the existing user with the scheduler
        	validateScheduler(scheduler);
            scheduler.setIsPaid(0);
            schedulerRepository.save(scheduler);
        } else {
            throw new UserNotFoundException("User Not Found!");
        }
    }

    /**
     * Updates an existing scheduled bill.
     *
     * @param scheduler the Scheduler object with updated information
     * @param user the User object associated with the scheduled bill
     */
    public void updateScheduler(int schedulerId, Scheduler updatedScheduler, int userId) {
        
        Scheduler existingScheduler = schedulerRepository.findById(schedulerId)
                .orElseThrow(() -> new IllegalArgumentException("Scheduler does not exist!"));

        // Check if the user is authorized to modify the scheduler
        if (existingScheduler.getUser() == null || existingScheduler.getUser().getUserId() != userId) {
            throw new AccessDeniedException("Not authorized to modify this scheduler!");
        }
        existingScheduler.setBillType(updatedScheduler.getBillType());
        existingScheduler.setCustomerId(updatedScheduler.getCustomerId());
        existingScheduler.setBillName(updatedScheduler.getBillName());
        existingScheduler.setPayeeAcc(updatedScheduler.getPayeeAcc());
        existingScheduler.setAmount(updatedScheduler.getAmount());
        existingScheduler.setDueDate(updatedScheduler.getDueDate());
        existingScheduler.setScheduledDate(updatedScheduler.getScheduledDate());
        existingScheduler.setIsRecurring(updatedScheduler.getIsRecurring());
        existingScheduler.setFrequency(updatedScheduler.getFrequency());
        existingScheduler.setEndAfter(updatedScheduler.getEndAfter());
        existingScheduler.setEndBy(updatedScheduler.getEndBy());
        existingScheduler.setIsPaid(updatedScheduler.getIsPaid());
        
        validateScheduler(existingScheduler);
        schedulerRepository.save(existingScheduler);
    }

    /**
     * Deletes scheduled bills by user and bill name.
     *
     * @param user the User object whose scheduled bills are to be deleted
     * @param billName the name of the bill to be deleted
     * @return true if any bills were deleted, false otherwise
     */
    public boolean deleteSchedulerByBillName(int userId, String billName) {
        Scheduler scheduler = schedulerRepository.findByUserUserIdAndBillName(userId, billName);

        if (scheduler==null) {
            throw new IllegalArgumentException("No scheduled bill found with the given bill name for the specified user.");
        }

        // Delete scheduler that match the bill name
        schedulerRepository.deleteById(scheduler.getSchedulerId());

        return true;
    }
    
    //utility functions
    public void validateScheduler(Scheduler scheduler) {
    	Date currDate = new Date();
    	String billName = scheduler.getBillName();
    	
    	if(scheduler.getUser().getBankDetails() == null) {
    		throw new IllegalArgumentException("Bank account not linked!");
    	}
    	if(scheduler.getPayeeAcc()==null) {
    		throw new IllegalArgumentException("Payee Account cannot be null!");
    	}
    	if(scheduler.getPayeeAcc()==null) {
    		throw new IllegalArgumentException("Payee Account cannot be null!");
    	}
    	if(billName==null) {
    		throw new IllegalArgumentException("Bill name cannot be null!");
    	}
    	if(getSchedulersByBillName(scheduler.getUser().getUserId(), billName) !=null) {
    		throw new IllegalArgumentException("You already have a scheduled payment with this bill name! Enter a new bill name!");
    	}
    	if(scheduler.getAmount() <= 0) {
    		throw new IllegalArgumentException("Invalid Payment Amount!");
    	}
    	if(scheduler.getDueDate()==null || scheduler.getScheduledDate()==null) {
    		throw new IllegalArgumentException("Scheduled/Due date cannot be null!");
    	}
    	if(scheduler.getDueDate().before(currDate)) {
    		throw new IllegalArgumentException("Due date must be after the current date!");
    	} 
    	if(scheduler.getScheduledDate().before(currDate)) {
    		throw new IllegalArgumentException("Scheduled date must be after the current date!");
    	} 
    	if(scheduler.getScheduledDate().after(scheduler.getDueDate())) {
    		throw new IllegalArgumentException("Scheduled date must be on/before the due date!");
    	} 
    	if(scheduler.getIsRecurring()==1 && scheduler.getFrequency()==null) {
    		throw new IllegalArgumentException("Payment frequency must be set for recurring payments!");
    	}
    }
}

