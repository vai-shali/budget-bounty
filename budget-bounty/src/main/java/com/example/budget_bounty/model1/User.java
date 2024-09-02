package com.example.budget_bounty.model1;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the primary key
    @Column(name = "user_id")  // Map to the database column
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private Integer role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PaymentTransaction> paymentTransactions;  // A user can have multiple transactions

//    private Bank bankDetails;

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

    // Getters and setters for all fields

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

    public List<PaymentTransaction> getPaymentTransactions() {
        return paymentTransactions;
    }

    public void setPaymentTransactions(List<PaymentTransaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
    }

//    public Bank getBankDetails() {
//        return bankDetails;
//    }
//
//    public void setBankDetails(Bank bankDetails) {
//        this.bankDetails = bankDetails;
//    }
//
//    @Override
//    public String toString() {
//        return "User [userId=" + userId + ", name=" + name + ", username=" + username
//                + ", phoneNumber=" + phoneNumber + ", email=" + email + ", role=" + role
//                + ", bankDetails=" + bankDetails + "]";
//    }
}
