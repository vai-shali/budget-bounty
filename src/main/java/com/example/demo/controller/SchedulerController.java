package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/schedulers")
public class SchedulerController {

    private final SchedulerService schedulerService;

    @Autowired
    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    /**
     * Retrieves all scheduled payments (admin functionality).
     *
     * @return a list of all scheduled payments
     */
    @GetMapping("/all")
    public ResponseEntity<List<Scheduler>> getAllScheduledPayments() {
        List<Scheduler> schedulers = schedulerService.getAllScheduledPayments();
        return ResponseEntity.ok(schedulers);
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
    public ResponseEntity<List<Scheduler>> getSchedulersByBillName(@PathVariable int userId, @RequestParam String billName) {
        User user = new User(); // Assuming User entity is already created, replace with actual User retrieval
        user.setUserId(userId);

        List<Scheduler> schedulers = schedulerService.getSchedulersByBillName(user, billName);
        return ResponseEntity.ok(schedulers);
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
        User user = new User(); // Assuming User entity is already created, replace with actual User retrieval
        user.setUserId(userId);

        schedulerService.addScheduler(scheduler, user);
        return ResponseEntity.status(201).body("Scheduler added successfully.");
    }

    /**
     * Updates an existing scheduled bill.
     *
     * @param scheduler the Scheduler object with updated information
     * @param userId the ID of the user associated with the scheduled bill
     * @return a response entity with the status of the update
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateScheduler(@RequestBody Scheduler scheduler, @PathVariable int userId) {
        User user = new User(); // Assuming User entity is already created, replace with actual User retrieval
        user.setUserId(userId);

        schedulerService.updateScheduler(scheduler, user);
        return ResponseEntity.ok("Scheduler updated successfully.");
    }

    /**
     * Deletes a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill
     * @return a response entity indicating the deletion status
     */
    @DeleteMapping("/delete/{schedulerId}")
    public ResponseEntity<String> deleteScheduler(@PathVariable int schedulerId) {
        schedulerService.deleteScheduler(schedulerId);
        return ResponseEntity.ok("Scheduler deleted successfully.");
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
        User user = new User(); // Assuming User entity is already created, replace with actual User retrieval
        user.setUserId(userId);

        boolean deleted = schedulerService.deleteSchedulerByBillName(user, billName);
        return deleted ? ResponseEntity.ok("Scheduled bill(s) deleted successfully.")
                : ResponseEntity.status(404).body("No scheduled bill found with the given bill name.");
    }
}
