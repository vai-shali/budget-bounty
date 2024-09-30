package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Scheduler;

/**
 * Repository class for managing Scheduler entities.
 * @author Vaishali
 * @since 2nd September 2024
 */
@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Integer> {

    /**
     * Retrieves scheduled bills for a specific user based on their user ID.
     *
     * @param userId the ID of the user whose scheduled bills are to be retrieved.
     * @return a list of Scheduler entities associated with the specified user ID.
     */
    @Query("FROM Scheduler WHERE user.userId = :userId")
    List<Scheduler> findByUserId(Integer userId);

    /**
     * Retrieves scheduled bills for a user based on the bill name.
     *
     * @param userId the ID of the user whose scheduled bills are to be retrieved.
     * @param billName the name of the bill to filter the scheduled bills.
     * @return a list of Scheduler entities associated with the specified user ID and bill name.
     */
    @Query("FROM Scheduler WHERE user.userId = :userId AND billName = :billName")
    Scheduler findByUserUserIdAndBillName(Integer userId, String billName);
    
    @Query(value = "SELECT * FROM scheduler WHERE TRUNC(scheduled_date) = TRUNC(SYSDATE) AND status = :status", nativeQuery = true)
    List<Scheduler> findByScheduledDateTodayAndStatus(@Param("status") String status);
    
//    List<Scheduler> findByScheduledDate(Date date);
    @Query(value = "SELECT * FROM scheduler WHERE TRUNC(scheduled_date) = TRUNC(:date)", nativeQuery = true)
    List<Scheduler> findByScheduledDate(@Param("date") Date date);



    
//    @Query(value = "SELECT * FROM scheduler WHERE TRUNC(scheduled_date) = TRUNC(SYSDATE)", nativeQuery = true)
//    List<Scheduler> findByScheduledDateToday();

}
