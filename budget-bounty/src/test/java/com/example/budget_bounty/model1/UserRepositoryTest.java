package com.example.budget_bounty.model1;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.budget_bounty.repository.UserRepository;
//import com.example.budget_bounty.service.UserService;


public class UserRepositoryTest {
	private static UserRepository obj;
	@BeforeAll
	public static void setup() {
		obj=new UserRepository();
	}
	@Test
	public void selectUserById() {
		try {
			// Testing a positive case
			User userObj= obj.findById(1);
			assertNotNull(userObj);
			assertEquals(userObj.getUserId(), new Integer(1));
			
			//Testing a negative case
			userObj=obj.findById(10);
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
			assertNotNull(obj.findByEmail("bob.johnson@example.com"));
			//Testing a negative case
			assertNull(obj.findByEmail("dosentexist@emp.com"));
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
			List<User> list=obj.findAll();
			int curSize=list.size();
			
			obj.save(newObj);
			
			list=obj.findAll();
			assertEquals(curSize+1,list.size());
			
			newObj.setName("sajjin");
			
			obj.update(newObj);
			User ret=obj.findById(6);
			assertEquals(newObj.getName(), ret.getName());
			
			obj.delete(6);
			
			list=obj.findAll();
			assertEquals(curSize,list.size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
