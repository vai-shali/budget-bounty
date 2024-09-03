package hiber.hiber.service;

import java.util.List;

import hiber.hiber.model.Scheduler;
import hiber.hiber.model.User;
import hiber.hiber.repository.SchedulerRepository;

/**
 * Service class for managing scheduled payments and bills.
 * Provides methods to perform CRUD operations on scheduled payments.
 */
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    /**
     * Default constructor to initialize the SchedulerRepository.
     * Creates a new instance of SchedulerRepository.
     */
    public SchedulerService() {
        this.schedulerRepository = new SchedulerRepository();
    }

    /**
     * Constructor to initialize the SchedulerRepository with a given instance.
     *
     * @param schedulerRepository the SchedulerRepository instance to use
     */
    public SchedulerService(SchedulerRepository schedulerRepository) {
        this.schedulerRepository = schedulerRepository;
    }

    /**
     * Retrieves all scheduled payments (admin functionality).
     *
     * @return a list of all Scheduler objects
     */
    public List<Scheduler> getAllScheduledPayments() {
        return schedulerRepository.getAllScheduledPayments();
    }

    /**
     * Deletes a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill to be deleted
     */
    public void deleteScheduler(int schedulerId) {
        schedulerRepository.deleteScheduler(schedulerId);
    }

    /**
     * Retrieves a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill to retrieve
     * @return the Scheduler object with the specified ID, or null if not found
     */
    public Scheduler getSchedulerById(int schedulerId) {
        return schedulerRepository.getSchedulerById(schedulerId);
    }

    /**
     * Retrieves all scheduled bills for a specific user by user ID.
     *
     * @param userId the ID of the user whose scheduled bills are to be retrieved
     * @return a list of Scheduler objects associated with the specified user ID
     */
    public List<Scheduler> getSchedulersByUserId(int userId) {
        return schedulerRepository.getSchedulersByUserId(userId);
    }

    /**
     * Retrieves scheduled bills for a user by bill name.
     *
     * @param user the User object whose scheduled bills are to be retrieved
     * @param billName the name of the bill to search for
     * @return a list of Scheduler objects that match the given bill name for the specified user
     */
    public List<Scheduler> getSchedulersByBillName(User user, String billName) {
        return schedulerRepository.getSchedulersByBillName(user, billName);
    }

    /**
     * Adds a new scheduled bill.
     *
     * @param scheduler the Scheduler object to be added
     * @param user the User object associated with the scheduled bill
     */
    public void addScheduler(Scheduler scheduler, User user) {
        scheduler.setUser(user);
        schedulerRepository.addScheduler(scheduler);
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
        Scheduler existingScheduler = schedulerRepository.getSchedulerById(scheduler.getSchedulerId());
        if (existingScheduler == null) {
            System.out.println("Scheduler with ID " + scheduler.getSchedulerId() + " does not exist.");
            return;
        }

        // Proceeding with the update
        schedulerRepository.updateScheduler(scheduler);
    }

    /**
     * Deletes scheduled bills by user and bill name.
     *
     * @param user the User object whose scheduled bills are to be deleted
     * @param billName the name of the bill to be deleted
     * @return true if any bills were deleted, false otherwise
     */
    public boolean deleteSchedulerByBillName(User user, String billName) {
        List<Scheduler> schedulers = schedulerRepository.getSchedulersByBillName(user, billName);

        if (schedulers.isEmpty()) {
            System.out.println("No scheduled bill found with the given bill name for the specified user.");
            return false;
        }

        // Delete all schedulers that match the bill name
        for (Scheduler scheduler : schedulers) {
            schedulerRepository.deleteScheduler(scheduler.getSchedulerId());
        }

        return true;
    }
}
