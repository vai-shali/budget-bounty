package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final SchedulerRepository schedulerRepository;

    /**
     * Default constructor to initialize the SchedulerRepository.
     * Creates a new instance of SchedulerRepository.
     */
    @Autowired
    public SchedulerService(SchedulerRepository schedulerRepository) {
        this.schedulerRepository = schedulerRepository;
    }


    /**
     * Retrieves all scheduled payments (admin functionality).
     *
     * @return a list of all Scheduler objects
     */
    public List<Scheduler> getAllScheduledPayments() {
        return schedulerRepository.findAll();
    }

    /**
     * Deletes a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill to be deleted
     */
    public void deleteScheduler(int schedulerId) {
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
    public List<Scheduler> getSchedulersByBillName(User user, String billName) {
        return schedulerRepository.findByUserUserIdAndBillName(user.getUserId(), billName);
    }

    /**
     * Adds a new scheduled bill.
     *
     * @param scheduler the Scheduler object to be added
     * @param user the User object associated with the scheduled bill
     */
    public void addScheduler(Scheduler scheduler, User user) {
        scheduler.setUser(user);
        schedulerRepository.save(scheduler);
    }

    /**
     * Updates an existing scheduled bill.
     *
     * @param scheduler the Scheduler object with updated information
     * @param user the User object associated with the scheduled bill
     */
    public void updateScheduler(Scheduler scheduler, User user) {
        scheduler.setUser(user);

        // Check if the scheduler exists
        if(!schedulerRepository.existsById(scheduler.getSchedulerId())) {
        	System.out.println("Scheduler with ID " + scheduler.getSchedulerId() + " does not exist.");
        	return;
        }

        // Proceeding with the update
        schedulerRepository.save(scheduler);
    }

    /**
     * Deletes scheduled bills by user and bill name.
     *
     * @param user the User object whose scheduled bills are to be deleted
     * @param billName the name of the bill to be deleted
     * @return true if any bills were deleted, false otherwise
     */
    public boolean deleteSchedulerByBillName(User user, String billName) {
        List<Scheduler> schedulers = schedulerRepository.findByUserUserIdAndBillName(user.getUserId(), billName);

        if (schedulers.isEmpty()) {
            System.out.println("No scheduled bill found with the given bill name for the specified user.");
            return false;
        }

        // Delete all schedulers that match the bill name
        for (Scheduler scheduler : schedulers) {
            schedulerRepository.deleteById(scheduler.getSchedulerId());
        }

        return true;
    }
}

