package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer>{
	Reward findByUserUserId(int userId);
}
