package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//to create a new user
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		userService.saveUser(user);
		return ResponseEntity.ok("User created successfully");
	}
	
	//to get all users
	@GetMapping("/list")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	//to get a user by id
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable int userId) {
		User u = userService.getUserById(userId);
		return ResponseEntity.ok(u);
	}
	
	//update existing user
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		try {
			userService.updateUser(user);
			return ResponseEntity.ok("User updated successfully");
		} catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//delete user by id
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {
		try {
			userService.deleteUser(userId);
			return ResponseEntity.ok("User deleted successfully");
		} catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//to check if a user exists by email
	@GetMapping("/exists/email/{email}")
	public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
		boolean exists = userService.existsByEmail(email);
		return ResponseEntity.ok(exists);
	}
	
	//to check if a user exists by email
	@GetMapping("/exists/username/{username}")
	public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
		boolean exists = userService.existsByUsername(username);
		return ResponseEntity.ok(exists);
	}
	
	//to find a user by email and password (check login by email & password)
	@PostMapping("/login/email")
	public ResponseEntity<User> findByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
		User user = userService.findByEmailAndPassword(email, password);
		if(user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(401).build();
		}
	}
	
	//to find a user by username and password (check login by username & password)
	@PostMapping("/login/username")
	public ResponseEntity<User> findByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
		User user = userService.findByUsernameAndPassword(username, password);
		if(user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(401).build();
		}
	}
}
