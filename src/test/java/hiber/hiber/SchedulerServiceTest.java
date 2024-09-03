package hiber.hiber;

import hiber.hiber.repository.SchedulerRepository;
import hiber.hiber.service.SchedulerService;

import hiber.hiber.model.Scheduler;
import hiber.hiber.model.User;

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
//        int userId = 1;
    	User userobj=new User();
        userobj.setUserId(1);
        String billName = "Electricity";
        List<Scheduler> mockSchedulers = new ArrayList<>();
        mockSchedulers.add(new Scheduler());
        when(schedulerRepository.getSchedulersByBillName(userobj, billName)).thenReturn(mockSchedulers);

        List<Scheduler> result = schedulerService.getSchedulersByBillName(userobj, billName);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    // Test addScheduler method
    @Test
    public void testAddScheduler() {
        Scheduler scheduler = new Scheduler();
//        int userId = 1;
        User userobj=new User();
        userobj.setUserId(1);
        schedulerService.addScheduler(scheduler, userobj);

        assertEquals(userobj.getUserId(), scheduler.getUser().getUserId());
        verify(schedulerRepository, times(1)).addScheduler(scheduler);
    }

    // Test updateScheduler method
    @Test
    public void testUpdateScheduler() {
        Scheduler scheduler = new Scheduler();
//        int userId = 1;
        User userobj=new User();
        userobj.setUserId(1);
        schedulerService.updateScheduler(scheduler, userobj);

        assertEquals(userobj.getUserId(), scheduler.getUser().getUserId());
        verify(schedulerRepository, times(1)).updateScheduler(scheduler);
    }

    // Test deleteSchedulerByBillName method
    @Test
    public void testDeleteSchedulerByBillName_Success() {
//        int userId = 1;
    	User userobj=new User();
        userobj.setUserId(1);
        String billName = "Electricity";
        List<Scheduler> mockSchedulers = new ArrayList<>();
        Scheduler mockScheduler = new Scheduler();
        mockScheduler.setSchedulerId(1);
        mockSchedulers.add(mockScheduler);
        when(schedulerRepository.getSchedulersByBillName(userobj, billName)).thenReturn(mockSchedulers);

        boolean result = schedulerService.deleteSchedulerByBillName(userobj, billName);

        assertTrue(result);
        verify(schedulerRepository, times(1)).deleteScheduler(mockScheduler.getSchedulerId());
    }

    @Test
    public void testDeleteSchedulerByBillName_NoSchedulersFound() {
//        int userId = 1;
        String billName = "Electricity";
        User userobj=new User();
        userobj.setUserId(1);
        when(schedulerRepository.getSchedulersByBillName(userobj, billName)).thenReturn(new ArrayList<>());

        boolean result = schedulerService.deleteSchedulerByBillName(userobj, billName);

        assertFalse(result);
    }
}