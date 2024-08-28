package com.example.budget_bounty.model1;

import com.example.budget_bounty.repository.SchedulerRepository;
import com.example.budget_bounty.service.SchedulerService;

import model1.Scheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SchedulerServiceTest {

    private SchedulerRepository schedulerRepository;
    private SchedulerService schedulerService;

    @BeforeEach
    public void setUp() {
        schedulerRepository = mock(SchedulerRepository.class);
        schedulerService = new SchedulerService(schedulerRepository);
    }

    // Test getAllScheduledPayments method
    @Test
    public void testGetAllScheduledPayments() {
        List<Scheduler> mockSchedulers = new ArrayList<>();
        mockSchedulers.add(new Scheduler());
        when(schedulerRepository.getAllScheduledPayments()).thenReturn(mockSchedulers);

        List<Scheduler> result = schedulerService.getAllScheduledPayments();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    // Test deleteScheduler method
    @Test
    public void testDeleteScheduler() {
        int schedulerId = 1;

        schedulerService.deleteScheduler(schedulerId);

        verify(schedulerRepository, times(1)).deleteScheduler(schedulerId);
    }

    // Test getSchedulersByUserId method
    @Test
    public void testGetSchedulersByUserId() {
        int userId = 1;
        List<Scheduler> mockSchedulers = new ArrayList<>();
        mockSchedulers.add(new Scheduler());
        when(schedulerRepository.getSchedulersByUserId(userId)).thenReturn(mockSchedulers);

        List<Scheduler> result = schedulerService.getSchedulersByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    // Test getSchedulersByBillName method
    @Test
    public void testGetSchedulersByBillName() {
        int userId = 1;
        String billName = "Electricity";
        List<Scheduler> mockSchedulers = new ArrayList<>();
        mockSchedulers.add(new Scheduler());
        when(schedulerRepository.getSchedulersByBillName(userId, billName)).thenReturn(mockSchedulers);

        List<Scheduler> result = schedulerService.getSchedulersByBillName(userId, billName);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    // Test addScheduler method
    @Test
    public void testAddScheduler() {
        Scheduler scheduler = new Scheduler();
        int userId = 1;

        schedulerService.addScheduler(scheduler, userId);

        assertEquals(userId, scheduler.getUserId());
        verify(schedulerRepository, times(1)).addScheduler(scheduler);
    }

    // Test updateScheduler method
    @Test
    public void testUpdateScheduler() {
        Scheduler scheduler = new Scheduler();
        int userId = 1;

        schedulerService.updateScheduler(scheduler, userId);

        assertEquals(userId, scheduler.getUserId());
        verify(schedulerRepository, times(1)).updateScheduler(scheduler);
    }

    // Test deleteSchedulerByBillName method
    @Test
    public void testDeleteSchedulerByBillName_Success() {
        int userId = 1;
        String billName = "Electricity";
        List<Scheduler> mockSchedulers = new ArrayList<>();
        Scheduler mockScheduler = new Scheduler();
        mockScheduler.setSchedulerId(1);
        mockSchedulers.add(mockScheduler);
        when(schedulerRepository.getSchedulersByBillName(userId, billName)).thenReturn(mockSchedulers);

        boolean result = schedulerService.deleteSchedulerByBillName(userId, billName);

        assertTrue(result);
        verify(schedulerRepository, times(1)).deleteScheduler(mockScheduler.getSchedulerId());
    }

    @Test
    public void testDeleteSchedulerByBillName_NoSchedulersFound() {
        int userId = 1;
        String billName = "Electricity";
        when(schedulerRepository.getSchedulersByBillName(userId, billName)).thenReturn(new ArrayList<>());

        boolean result = schedulerService.deleteSchedulerByBillName(userId, billName);

        assertFalse(result);
    }
}