package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bank;
import com.example.demo.model.PaymentTransaction;
import com.example.demo.model.Reward;
import com.example.demo.model.User;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.PaymentTransactionRepository;
import com.example.demo.repository.RewardRepository;
import com.example.demo.repository.UserRepository;

@Service
public class RewardService {
	
	@Autowired
	private RewardRepository rewardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private PaymentTransactionRepository transactionRepository;
	
	public void addReward(Reward reward, int userId) {
		User existingUser = userRepository.findById(userId).orElse(null);
		
		if(existingUser==null) {
			throw new IllegalArgumentException("User not found!");
		}
		reward.setUser(existingUser);
		reward.setTotalPoints(0);
		reward.setLastUpdated(LocalDateTime.now());
		rewardRepository.save(reward);
	}
	
	public void addPoints(int points, int userId) {
	    Reward reward = rewardRepository.findByUserUserId(userId);
	    User existingUser = userRepository.findById(userId).orElse(null);

	    if (existingUser == null) {
	        throw new IllegalArgumentException("User not found!");
	    }

	    if (reward == null) {
	        reward = new Reward();	// Create a new reward if it doesn't exist
	        reward.setUser(existingUser);  // Associate the user
	        reward.setTotalPoints(points);  // Start with the points being added
	    } else {
	        reward.setTotalPoints(reward.getTotalPoints() + points); // Add points to the existing reward
	    }
	    reward.setLastUpdated(LocalDateTime.now()); // Update the last updated timestamp
	    rewardRepository.save(reward);
	}
	
	public void redeemReward(int userId) {
		Reward reward = rewardRepository.findByUserUserId(userId);
		User user = userRepository.findById(userId).orElse(null);
        Bank bank = bankRepository.findByUserUserId(userId).orElse(null);

        if (user == null || bank == null) {
            throw new IllegalArgumentException("User or Bank account not found!");
        }
		
		if(reward==null || reward.getTotalPoints()==0) {
			throw new IllegalArgumentException("No rewards found!");
		} else {
			int points = reward.getTotalPoints();
			Double conversionRate = 0.1;
			Double amount = points*conversionRate;
			
			try {
				bank.setBalance(bank.getBalance() + amount);
				bankRepository.save(bank);
				reward.setTotalPoints(0);
				rewardRepository.save(reward);
				PaymentTransaction transaction = new PaymentTransaction(user, new Date(), "Reward points",
                        "system", bank.getAccountNumber(), "credited", amount, UUID.randomUUID().toString(), "success");
				transactionRepository.save(transaction);
			} catch(Exception e) {
				throw new RuntimeException("An error occured while redeeming transaction! Please try again later!");
			}
		}
	}

	
	// Method to get the reward points for a specific user
    public Reward getRewardByUserId(int userId) {
        Reward reward = rewardRepository.findByUserUserId(userId);
        if(reward!=null) {
        	return reward;
        } else {
        	throw new IllegalArgumentException("Reward not found for user id: " + userId);
        }
    }

    // Method to delete a reward record
    public void deleteReward(int userId) {
    	Reward reward = rewardRepository.findByUserUserId(userId);
        if(reward!=null) {
        	rewardRepository.delete(reward);
        } else {
        	throw new IllegalArgumentException("Reward not found for user id: " + userId);
        }
    }
    
    public List<Reward> getAllRewards() {
    	List<Reward> rewards = rewardRepository.findAll();
    	return rewards;
    }
}
