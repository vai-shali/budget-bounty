package hiber.hiber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import hiber.hiber.service.UserService;
import hiber.hiber.model.User;

/**
 * Unit tests for the {@link UserService} class.
 * <p>
 * This class tests the functionality of the {@link UserService} methods
 * using mock data and the actual service implementation.
 * </p>
 * @author Vishal
 * @since 2nd September, 2024
 */
public class UserServiceTest {
	private static UserService obj;

	/**
	 * Set up method to initialize the {@link UserService} instance before all tests.
	 */
	@BeforeAll
	public static void setup() {
		try {
			obj = new UserService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test for {@link UserService#getUserById(int)} method.
	 * <p>
	 * Verifies that the method correctly retrieves a user by ID and handles both positive and negative cases.
	 * </p>
	 */
	@Test
	public void selectUserById() {
		try {
			// Testing a positive case
			User userObj = obj.getUserById(1);
			assertNotNull(userObj);
			assertEquals(userObj.getUserId(), new Integer(1));

			// Testing a negative case
			userObj = obj.getUserById(10);
			assertNull(userObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test for {@link UserService#existsByEmail(String)} method.
	 * <p>
	 * Verifies that the method correctly checks the presence of an email and handles both positive and negative cases.
	 * </p>
	 */
	@Test
	public void checkEmailPresent() {
		try {
			// Testing a positive case
			assertTrue(obj.existsByEmail("bob.johnson@example.com"));
			// Testing a negative case
			assertFalse(obj.existsByEmail("dosentexist@emp.com"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test for {@link UserService#findByEmailAndPassword(String, String)} method.
	 * <p>
	 * Verifies that the method correctly validates email and password and handles both positive and negative cases.
	 * </p>
	 */
	@Test
	public void validateEmailandPassword() {
		try {
			// Testing a positive case
			User userobj = obj.findByEmailAndPassword("bob.johnson@example.com", "bob1");
			assertNotNull(userobj);
			assertEquals(userobj.getEmail(), "bob.johnson@example.com");
			assertEquals(userobj.getPassword(), "bob1");

			// Testing a negative case
			userobj = obj.findByEmailAndPassword("bob.johnson@example.com", "bob123");
			assertNull(userobj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test for add, update, and delete operations in {@link UserService}.
	 * <p>
	 * Verifies that the methods correctly add, update, and delete a user and handle the size of the user list accordingly.
	 * </p>
	 */
	@Test
	public void testAddUpdateDelete() {
		try {
			User newObj = new User(6, "name", "username", "password", "0123654789", "email", 0);
			List<User> list = obj.getAllUsers();
			int curSize = list.size();

			obj.saveUser(newObj);

			list = obj.getAllUsers();
			assertEquals(curSize + 1, list.size());

			newObj.setName("sajjin");

			obj.updateUser(newObj);
			User ret = obj.getUserById(6);
			assertEquals(newObj.getName(), ret.getName());

			obj.deleteUser(6);

			list = obj.getAllUsers();
			assertEquals(curSize, list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
