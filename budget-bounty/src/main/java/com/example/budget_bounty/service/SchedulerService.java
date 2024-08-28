package com.example.budget_bounty.service;

import java.util.List;

import com.example.budget_bounty.repository.SchedulerRepository;

import model1.Scheduler;

public class SchedulerService {
	
	private final SchedulerRepository schedulerRepository; 
	
	//constructor
	public SchedulerService()
	{
		this.schedulerRepository=new SchedulerRepository();
	}
	public SchedulerService(SchedulerRepository scr) {
		this.schedulerRepository=scr;	
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
    public List<Scheduler> getSchedulersByUserId(int userId) {
        return schedulerRepository.getSchedulersByUserId(userId);
    }

    // Method to get scheduled bills for a user by bill name
    public List<Scheduler> getSchedulersByBillName(int userId, String billName) {
        return schedulerRepository.getSchedulersByBillName(userId, billName);
    }

    // Method to add a new scheduled bill
    public void addScheduler(Scheduler scheduler, int userId) {
        scheduler.setUserId(userId);
        schedulerRepository.addScheduler(scheduler);
    }

    // Method to update an existing scheduled bill
    public void updateScheduler(Scheduler scheduler, int userId) {
        scheduler.setUserId(userId);
        schedulerRepository.updateScheduler(scheduler);
    }
    
    // Method to delete a scheduled bill by user ID and bill name
    public boolean deleteSchedulerByBillName(int userId, String billName) {
        List<Scheduler> schedulers = schedulerRepository.getSchedulersByBillName(userId, billName);
        
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
