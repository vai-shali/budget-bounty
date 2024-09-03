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

/**
 * Unit tests for the {@link SchedulerService} class.
 * <p>
 * This class tests the functionality of the {@link SchedulerService} methods
 * using mock data and Mockito for interaction with the {@link SchedulerRepository}.
 * </p>
 * @author Amrisha
 * @since 2nd September, 2024
 */
public class SchedulerServiceTest {

    private SchedulerRepository schedulerRepository;
    private SchedulerService schedulerService;

    /**
     * Set up method to initialize the {@link SchedulerRepository} mock and
     * create a {@link SchedulerService} instance before each test.
     */
    @BeforeEach
    public void setUp() {
        schedulerRepository = mock(SchedulerRepository.class);
        schedulerService = new SchedulerService(schedulerRepository);
    }

    /**
     * Test for {@link SchedulerService#getAllScheduledPayments()} method.
     * <p>
     * Verifies that the method correctly retrieves a list of all scheduled payments.
     * </p>
     */
    @Test
    public void testGetAllScheduledPayments() {
        List<Scheduler> mockSchedulers = new ArrayList<>();
        mockSchedulers.add(new Scheduler());
        when(schedulerRepository.getAllScheduledPayments()).thenReturn(mockSchedulers);

        List<Scheduler> result = schedulerService.getAllScheduledPayments();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test for {@link SchedulerService#deleteScheduler(int)} method.
     * <p>
     * Verifies that the method calls the repository's deleteScheduler method with
     * the correct scheduler ID.
     * </p>
     */
    @Test
    public void testDeleteScheduler() {
        int schedulerId = 1;

        schedulerService.deleteScheduler(schedulerId);

        verify(schedulerRepository, times(1)).deleteScheduler(schedulerId);
    }

    /**
     * Test for {@link SchedulerService#getSchedulersByUserId(int)} method.
     * <p>
     * Verifies that the method correctly retrieves a list of schedulers by user ID.
     * </p>
     */
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

    /**
     * Test for {@link SchedulerService#getSchedulersByBillName(User, String)} method.
     * <p>
     * Verifies that the method correctly retrieves a list of schedulers by bill name.
     * </p>
     */
    @Test
    public void testGetSchedulersByBillName() {
        User userobj = new User();
        userobj.setUserId(1);
        String billName = "Electricity";
        List<Scheduler> mockSchedulers = new ArrayList<>();
        mockSchedulers.add(new Scheduler());
        when(schedulerRepository.getSchedulersByBillName(userobj, billName)).thenReturn(mockSchedulers);

        List<Scheduler> result = schedulerService.getSchedulersByBillName(userobj, billName);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test for {@link SchedulerService#addScheduler(Scheduler, User)} method.
     * <p>
     * Verifies that the method correctly associates a user with a scheduler and
     * calls the repository's addScheduler method.
     * </p>
     */
    @Test
    public void testAddScheduler() {
        Scheduler scheduler = new Scheduler();
        User userobj = new User();
        userobj.setUserId(1);
        schedulerService.addScheduler(scheduler, userobj);

        assertEquals(userobj.getUserId(), scheduler.getUser().getUserId());
        verify(schedulerRepository, times(1)).addScheduler(scheduler);
    }

    /**
     * Test for {@link SchedulerService#updateScheduler(Scheduler, User)} method.
     * <p>
     * Verifies that the method correctly updates a scheduler with user details
     * and calls the repository's updateScheduler method.
     * </p>
     */
    @Test
    public void testUpdateScheduler() {
        Scheduler scheduler = new Scheduler();
        User userobj = new User();
        userobj.setUserId(1);
        schedulerService.updateScheduler(scheduler, userobj);

        assertEquals(userobj.getUserId(), scheduler.getUser().getUserId());
        verify(schedulerRepository, times(1)).updateScheduler(scheduler);
    }

    /**
     * Test for {@link SchedulerService#deleteSchedulerByBillName(User, String)} method.
     * <p>
     * Verifies that the method successfully deletes a scheduler by bill name and
     * user details, and calls the repository's deleteScheduler method.
     * </p>
     */
    @Test
    public void testDeleteSchedulerByBillName_Success() {
        User userobj = new User();
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

    /**
     * Test for {@link SchedulerService#deleteSchedulerByBillName(User, String)} method when no schedulers are found.
     * <p>
     * Verifies that the method returns false when no schedulers are found for the given
     * bill name and user details.
     * </p>
     */
    @Test
    public void testDeleteSchedulerByBillName_NoSchedulersFound() {
        String billName = "Electricity";
        User userobj = new User();
        userobj.setUserId(1);
        when(schedulerRepository.getSchedulersByBillName(userobj, billName)).thenReturn(new ArrayList<>());

        boolean result = schedulerService.deleteSchedulerByBillName(userobj, billName);

        assertFalse(result);
    }
}
