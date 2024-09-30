package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.AccessDeniedException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Scheduler;
import com.example.demo.model.User;
import com.example.demo.service.SchedulerService;

/**
 * Controller class for handling HTTP requests related to scheduled payments and bills.
 * Provides endpoints to perform CRUD operations.
 * @author Vishal Lodha
 * @since 12th September, 2024
 */
@RestController
@RequestMapping("/scheduler")
@CrossOrigin
public class SchedulerController {

	@Autowired
    private SchedulerService schedulerService;

    /**
     * Retrieves all scheduled payments (admin functionality).
     *
     * @return a list of all scheduled payments
     */
    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getAllScheduledPayments(@PathVariable int userId) {
    	try {
    		List<Scheduler> schedulers = schedulerService.getAllScheduledPayments(userId);
            return ResponseEntity.ok(schedulers);
    	} catch (AccessDeniedException e) {
        	return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error occurred!"); // Generic 500 error
        }
    }

    /**
     * Retrieves a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill
     * @return the scheduled bill if found, or 404 if not found
     */
    @GetMapping("/{schedulerId}")
    public ResponseEntity<Scheduler> getSchedulerById(@PathVariable int schedulerId) {
        Scheduler scheduler = schedulerService.getSchedulerById(schedulerId);
        return scheduler != null ? ResponseEntity.ok(scheduler) : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves all scheduled bills for a specific user by user ID.
     *
     * @param userId the ID of the user
     * @return a list of scheduled bills for the user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Scheduler>> getSchedulersByUserId(@PathVariable int userId) {
        List<Scheduler> schedulers = schedulerService.getSchedulersByUserId(userId);
        return ResponseEntity.ok(schedulers);
    }

    /**
     * Retrieves scheduled bills for a user by bill name.
     *
     * @param userId the ID of the user
     * @param billName the name of the bill
     * @return a list of scheduled bills matching the bill name for the user
     */
    @GetMapping("/user/{userId}/bill")
    public ResponseEntity<?> getSchedulersByBillName(@PathVariable int userId, @RequestParam String billName) {
        Scheduler scheduler = schedulerService.getSchedulersByBillName(userId, billName);
        if (scheduler == null) {
            // Return 404 status with a message when no scheduler is found
            return ResponseEntity.status(404).body("No scheduled payment found for the specified bill name and user.");
        }
        return ResponseEntity.ok(scheduler);
    }

    /**
     * Adds a new scheduled bill.
     *
     * @param scheduler the Scheduler object to be added
     * @param userId the ID of the user associated with the scheduled bill
     * @return a response entity with the created status
     */
    @PostMapping("/add/{userId}")
    public ResponseEntity<String> addScheduler(@RequestBody Scheduler scheduler, @PathVariable int userId) {
        try {
            schedulerService.addScheduler(scheduler, userId);
            return ResponseEntity.status(201).body("Scheduler added successfully.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalArgumentException e) {
        	return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error occurred!"); // Generic 500 error
        }
    }

    /**
     * Updates an existing scheduled bill.
     *
     * @param scheduler the Scheduler object with updated information
     * @param userId the ID of the user associated with the scheduled bill
     * @return a response entity with the status of the update
     */
    @PutMapping("/update/{schedulerId}")
    public ResponseEntity<String> updateScheduler(@PathVariable int schedulerId, @RequestBody Scheduler scheduler, @RequestParam int userId) {
        try {
            schedulerService.updateScheduler(schedulerId, scheduler, userId);
            return ResponseEntity.status(201).body("Scheduler updated successfully.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalArgumentException e) {
        	return ResponseEntity.status(404).body(e.getMessage());
        } 
        catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error occurred!\n"+e.getMessage()); // Generic 500 error
        }
    }

    /**
     * Deletes a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill
     * @return a response entity indicating the deletion status
     */
    @DeleteMapping("/delete/{schedulerId}")
    public ResponseEntity<String> deleteScheduler(@PathVariable int schedulerId) {
    	try {
            schedulerService.deleteScheduler(schedulerId);
            return ResponseEntity.ok("Scheduler deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error occurred! "+e.getLocalizedMessage()); // Generic 500 error
        }
    }

    /**
     * Deletes scheduled bills by user and bill name.
     *
     * @param userId the ID of the user
     * @param billName the name of the bill to be deleted
     * @return a response entity indicating if any bills were deleted
     */
    @DeleteMapping("/delete/user/{userId}/bill")
    public ResponseEntity<String> deleteSchedulerByBillName(@PathVariable int userId, @RequestParam String billName) {
    	try {
    		schedulerService.deleteSchedulerByBillName(userId, billName);
    		return ResponseEntity.ok("Scheduler deleted successfully.");
    	} catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error occurred!"); // Generic 500 error
        }
    }
}
