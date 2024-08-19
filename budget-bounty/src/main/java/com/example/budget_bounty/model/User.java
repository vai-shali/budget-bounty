package com.example.budget_bounty.model;

import java.util.Scanner;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private int id;
    private String phone;
    private String email;
    private String password;

    public User(String firstName, String lastName, String username, int id, String phone, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User() {}

    public void inputUserDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        this.firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        this.lastName = scanner.nextLine();

        System.out.print("Enter Username: ");
        this.username = scanner.nextLine();

        System.out.print("Enter ID: ");
        this.id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Phone Number: ");
        this.phone = scanner.nextLine();

        System.out.print("Enter Email: ");
        this.email = scanner.nextLine();

        System.out.print("Enter Password: ");
        this.password = scanner.nextLine();
        scanner.close();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to schedule a payment with a PaymentScheduler instance
    public void schedulePayment(Bill bill, String paymentDate, PaymentScheduler scheduler) {
        scheduler.addBill(bill);
        System.out.println("Payment of $" + bill.getTotalAmount() + " scheduled for " + paymentDate + " for bill ID: " + bill.getId());
    }

    // Method to make a payment
    public void makePayment(Bill bill) {
        System.out.println("Payment of $" + bill.getTotalAmount() + " made for bill ID: " + bill.getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}