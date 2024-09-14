package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * @author Inas
 * @since 2nd September
 * The User class represents a user entity for persistence in a database.
 * It includes user-related details such as personal information, contact details,
 * role, bank details, payment transactions, and schedulers.
 */
@Entity
@Table(name = "users")
public class User {

	/**
	 * The unique identifier for the user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the primary key
	@Column(name="user_id")
	private Integer userId;

	/**
	 * The name of the user.
	 */
	private String name;

	/**
	 * The username used for the user's account.
	 */
	private String username;

	/**
	 * The password for the user's account.
	 */
	private String password;

	/**
	 * The phone number of the user.
	 */
	@Column(name = "phone_number")
	private String phoneNumber;

	/**
	 * The email address of the user.
	 */
	private String email;

	/**
	 * The role of the user, represented as an integer.
	 */
	private Integer role;

	/**
	 * The bank details associated with the user.
	 */
	@OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Bank bankDetails;

	/**
	 * The list of payment transactions associated with the user.
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private List<PaymentTransaction> paymentTransactions;

	/**
	 * The list of schedulers associated with the user.
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private List<Scheduler> schedulers;

	/**
	 * Default constructor for creating a user object.
	 */
	public User() {}

	/**
	 * Constructs a User object with the specified parameters.
	 *
	 * @param userId the unique identifier for the user
	 * @param name the name of the user
	 * @param username the username of the user
	 * @param password the password of the user
	 * @param phoneNumber the phone number of the user
	 * @param email the email address of the user
	 * @param role the role of the user
	 */
	public User(Integer userId, String name, String username, String password, String phoneNumber, String email, Integer role) {
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.role = role;
	}

	public User(String name, String username, String password, String phoneNumber, String email, Integer role) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.role = role;
	}

	/**
	 * Gets the unique identifier for the user.
	 *
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the unique identifier for the user.
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Gets the name of the user.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the user.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the username of the user.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password of the user.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the phone number of the user.
	 *
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number of the user.
	 *
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the email address of the user.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the user.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the role of the user.
	 *
	 * @return the role
	 */
	public Integer getRole() {
		return role;
	}

	/**
	 * Sets the role of the user.
	 *
	 * @param role the role to set
	 */
	public void setRole(Integer role) {
		this.role = role;
	}

	/**
	 * Gets the bank details associated with the user.
	 *
	 * @return the bankDetails
	 */
	public Bank getBankDetails() {
		return bankDetails;
	}

	/**
	 * Sets the bank details associated with the user.
	 *
	 * @param bankDetails the bankDetails to set
	 */
	public void setBankDetails(Bank bankDetails) {
		this.bankDetails = bankDetails;
//	}
//	public Set<Bank> getBankDetails() {
//		return bankDetails;
//	}
//
//	public void setBankDetails(Set<Bank> bankDetails) {
//		this.bankDetails = bankDetails;
	}

	/**
	 * Returns a string representation of the User object.
	 *
	 * @return a string representing the User object
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", username=" + username
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", role=" + role
				+ ", bankDetails=" + bankDetails + "]";
	}
}

