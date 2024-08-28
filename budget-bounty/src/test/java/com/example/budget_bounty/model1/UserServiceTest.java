package com.example.budget_bounty.model1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.budget_bounty.service.UserService;

import model1.User;

public class UserServiceTest {
	private static UserService obj;
	@BeforeAll
	public static void setup() {
		try {
			obj=new UserService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void selectUserById() {
		try {
			// Testing a positive case
			User userObj= obj.getUserById(1);
			assertNotNull(userObj);
			assertEquals(userObj.getUserId(), new Integer(1));
			
			//Testing a negative case
			userObj=obj.getUserById(10);
			assertNull(userObj);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void checkEmailPresent() {
		try {
			//Testing a positive case
			assertTrue(obj.existsByEmail("bob.johnson@example.com"));
			//Testing a negative case
			assertFalse(obj.existsByEmail("dosentexist@emp.com"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void validateEmailandPassword() {
		try {
			//testing a positive case
			User userobj=obj.findByEmailAndPassword("bob.johnson@example.com", "bob1");
			assertNotNull(userobj);
			assertEquals(userobj.getEmail(),"bob.johnson@example.com");
			assertEquals(userobj.getPassword(),"bob1");
			
			//testing a negative case
			userobj=obj.findByEmailAndPassword("bob.johnson@example.com", "bob123");
			assertNull(userobj);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testAddUpdateDelete() {
		try {
			User newObj=new User(6,"name","username","password","0123654789","email",0);
			List<User> list=obj.getAllUsers();
			int curSize=list.size();
			
			obj.saveUser(newObj);
			
			list=obj.getAllUsers();
			assertEquals(curSize+1,list.size());
			
			newObj.setName("sajjin");
			
			obj.updateUser(newObj);
			User ret=obj.getUserById(6);
			assertEquals(newObj.getName(), ret.getName());
			
			obj.deleteUser(6);
			
			list=obj.getAllUsers();
			assertEquals(curSize,list.size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
