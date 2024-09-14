package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PaymentTransaction;

/**
 * Repository class for managing PaymentTransaction entities.
 * @author Vaishali
 * @since 2nd September, 2024
 */
@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Integer> {
    
    /**
     * Finds PaymentTransaction entities based on the user ID.
     *
     * @param userId the ID of the user whose PaymentTransactions are to be retrieved.
     * @return a list of PaymentTransaction entities associated with the specified user ID.
     */
    List<PaymentTransaction> findByUserUserId(Integer userId);
}
