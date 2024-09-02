package hiber.hiber.model;

import java.util.Set;

import hiber.hiber.model.Bank;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="users")
public class User {
	@Id
	@Column(name="user_id")
	private Integer userId;
    private String name;
    private String username;
    private String password;
    @Column(name="phone_number")
    private String phoneNumber;
    private String email;
    private Integer role;
//    @Transient
//    private Bank bankDetails;  // Add a field for Bank details
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Bank bankDetails;
    // Constructor without parameters
    public User() {}

    // Constructor with parameters
    public User(Integer userId, String name, String username, String password, String phoneNumber, String email, Integer role) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	
	public Bank getBankDetails() {
		return bankDetails;
	}

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

	@Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", username=" + username
                + ", phoneNumber=" + phoneNumber + ", email=" + email + ", role=" + role
                + ", bankDetails=" + bankDetails + "]";  // Include bankDetails in the toString output
    }
}

