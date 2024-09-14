package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bank")
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the primary key
	@Column(name="bank_id")
    private int bankId;
    @Column(name="bank_name")
	private String bankName;
    private String ifsc;

    /**
     * Account number associated with the bank.
     */
    @Column(name = "account_number")
    private String accountNumber;

    /**
     * UPI ID linked with the bank account.
     */
    @Column(name = "upi_id")
    private String upiId;

    /**
     * Type of the bank account (e.g., savings, current).
     */
    @Column(name = "account_type")
    private String accountType;

    /**
     * Balance available in the bank account.
     */
    private double balance;

    /**
     * The user associated with the bank account.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    /**
     * Default constructor for the Bank class.
     */
    public Bank() {
        super();
    }

    /**
     * Constructor to initialize all fields of the Bank class.
     *
     * @param bankName      the name of the bank
     * @param ifsc          the IFSC code of the bank
     * @param accountNumber the account number associated with the bank
     * @param upiId         the UPI ID linked with the bank account
     * @param accountType   the type of the bank account (e.g., savings, current)
     * @param balance       the balance available in the bank account
     * @param userObj       the user associated with the bank account
     */
    public Bank(int bankId,String bankName, String ifsc, String accountNumber, String upiId, String accountType, double balance, User userObj) {
        this.bankId=bankId;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.accountNumber = accountNumber;
        this.upiId = upiId;
        this.accountType = accountType;
        this.balance = balance;
        this.user=userObj;
    }

    public Bank(String bankName, String ifsc, String accountNumber, String upiId, String accountType, double balance, User userObj) {
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.accountNumber = accountNumber;
        this.upiId = upiId;
        this.accountType = accountType;
        this.balance = balance;
        this.user=userObj;
    }

    /**
     * Gets the unique identifier for the bank.
     *
     * @return the bank ID
     */
    public int getBankId() {
        return bankId;
    }

    /**
     * Sets the unique identifier for the bank.
     *
     * @param bankId the bank ID to set
     */
    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    /**
     * Gets the name of the bank.
     *
     * @return the bank name
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the name of the bank.
     *
     * @param bankName the bank name to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * Gets the IFSC code of the bank.
     *
     * @return the IFSC code
     */
    public String getIfsc() {
        return ifsc;
    }

    /**
     * Sets the IFSC code of the bank.
     *
     * @param ifsc the IFSC code to set
     */
    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    /**
     * Gets the account number associated with the bank.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number associated with the bank.
     *
     * @param accountNumber the account number to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the UPI ID linked with the bank account.
     *
     * @return the UPI ID
     */
    public String getUpiId() {
        return upiId;
    }

    /**
     * Sets the UPI ID linked with the bank account.
     *
     * @param upiId the UPI ID to set
     */
    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    /**
     * Gets the type of the bank account.
     *
     * @return the account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the type of the bank account.
     *
     * @param accountType the account type to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the balance available in the bank account.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance available in the bank account.
     *
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the user associated with the bank account.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the bank account.
     *
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns a string representation of the Bank object.
     *
     * @return a string containing the bank details
     */
    @Override
    public String toString() {
        return "Bank [bankId=" + bankId + ", bankName=" + bankName + ", ifsc=" + ifsc + ", accountNumber="
                + accountNumber + ", upiId=" + upiId + ", accountType=" + accountType + ", balance=" + balance + "]";
    }
}

