package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Reward;
import com.example.demo.service.RewardService;

@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    // Endpoint to add a reward
    @PostMapping("/add")
    public ResponseEntity<String> addReward(@RequestBody Reward reward, @RequestParam int userId) {
        try {
            rewardService.addReward(reward, userId);
            return ResponseEntity.ok("Reward added successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the reward.\n"+e.getMessage());
        }
    }

    // Endpoint to add points
    @PostMapping("/addPoints")
    public ResponseEntity<String> addPoints(@RequestParam int points, @RequestParam int userId) {
        try {
            rewardService.addPoints(points, userId);
            return ResponseEntity.ok("Points added successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding points.");
        }
    }

    // Endpoint to redeem reward points
    @PostMapping("/redeem/{userId}")
    public ResponseEntity<String> redeemReward(@PathVariable int userId) {
        try {
            rewardService.redeemReward(userId);
            return ResponseEntity.ok("Reward redeemed successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Endpoint to get reward by user ID
    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getRewardByUserId(@PathVariable int userId) {
        try {
            Reward reward = rewardService.getRewardByUserId(userId);
            return ResponseEntity.ok(reward);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint to delete a reward record
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteReward(@PathVariable int userId) {
        try {
            rewardService.deleteReward(userId);
            return ResponseEntity.ok("Reward deleted successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the reward.");
        }
    }

    // Endpoint to get all rewards
    @GetMapping("/list")
    public ResponseEntity<List<Reward>> getAllRewards() {
        List<Reward> rewards = rewardService.getAllRewards();
        return ResponseEntity.ok(rewards);
    }
}

