package hiber.hiber.service;

import java.util.List;

import hiber.hiber.model.Scheduler;
import hiber.hiber.model.User;
import hiber.hiber.repository.SchedulerRepository;

public class SchedulerService {
	
	private final SchedulerRepository schedulerRepository; 
	
	//constructor
	public SchedulerService()
	{
		this.schedulerRepository=new SchedulerRepository();
	}
	public SchedulerService(SchedulerRepository schedulerRepository) {
		this.schedulerRepository=schedulerRepository;	
	}
	
	//getting all scheduled payments (admin functionality)
	public List<Scheduler> getAllScheduledPayments() {
		return schedulerRepository.getAllScheduledPayments();
	}
	
	// Method to delete a scheduled bill by ID
    public void deleteScheduler(int schedulerId) {
        schedulerRepository.deleteScheduler(schedulerId);
    }
    
 // Method to get all scheduled bills for a specific user by user ID
    public Scheduler getSchedulerById(int schedulerId) {
        return schedulerRepository.getSchedulerById(schedulerId);
    }

    // Method to get all scheduled bills for a specific user by user ID
    public List<Scheduler> getSchedulersByUserId(int userId) {
        return schedulerRepository.getSchedulersByUserId(userId);
    }

    // Method to get scheduled bills for a user by bill name
    public List<Scheduler> getSchedulersByBillName(User user, String billName) {
        return schedulerRepository.getSchedulersByBillName(user, billName);
    }

 // Method to add a new scheduled bill
    public void addScheduler(Scheduler scheduler, User user) {
        scheduler.setUser(user);
        schedulerRepository.addScheduler(scheduler);
    }

    // Method to update an existing scheduled bill
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
    
    // Method to delete a scheduled bill by user ID and bill name
    public boolean deleteSchedulerByBillName(User user, String billName) {
        List<Scheduler> schedulers = schedulerRepository.getSchedulersByBillName(user, billName);
        
        if (schedulers.isEmpty()) {
            System.out.println("No scheduled bill found with the given bill name for the specified user.");
            return false;
        }

        // delete all schedulers that match the bill name
        for (Scheduler scheduler : schedulers) {
            schedulerRepository.deleteScheduler(scheduler.getSchedulerId());
        }
        
        return true;
    }
}
