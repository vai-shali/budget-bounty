package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reward")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reward_id")
    private Integer rewardId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="total_points")
    private Integer totalPoints;  // Total reward points accumulated by the user

    @Column(name="last_updated")
    private LocalDateTime lastUpdated;  // Timestamp for the last time points were updated

    public Reward() {
    }

    public Reward(User user, Integer totalPoints, LocalDateTime lastUpdated) {
        this.user = user;
        this.totalPoints = totalPoints;
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters

    public Integer getId() {
        return rewardId;
    }

    public void setId(Integer id) {
        this.rewardId = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

