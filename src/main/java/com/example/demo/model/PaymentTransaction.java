package com.example.demo.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a payment transaction made by a user.
 * This class is mapped to the 'transaction' table in the database.
 * It contains details such as the transaction date, name, accounts involved,
 * transaction type, amount, and reference number.
 *
 * The class also includes a Many-to-One relationship with the User class,
 * indicating that multiple transactions can be associated with a single user.
 *
 * @author Vaishali
 * @since 2nd September, 2024
 */
@Entity
@Table(name = "transaction")
public class PaymentTransaction {

    /**
     * Unique identifier for the transaction.
     * This value is auto-generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    /**
     * User who made the transaction.
     * This is a foreign key in the 'transaction' table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Date and time when the transaction occurred.
     */
    @Column(name = "transaction_date")
    private Date transactionDate;

    /**
     * Name or description of the transaction.
     */
    @Column(name = "transaction_name")
    private String transactionName;

    /**
     * Account number from which the transaction was made.
     */
    @Column(name = "from_account_number")
    private String fromAccountNumber;

    /**
     * Account number to which the transaction was made.
     */
    @Column(name = "to_account_number")
    private String toAccountNumber;

    /**
     * Type of transaction (e.g., debit, credit).
     */
    @Column(name = "transaction_type")
    private String transactionType;

    /**
     * Amount of money involved in the transaction.
     */
    @Column(name = "amount")
    private Double amount;

    /**
     * Reference number associated with the transaction.
     */
    @Column(name = "reference_number")
    private String referenceNumber;

    /**
     * Default constructor for JPA.
     */
    public PaymentTransaction() {
        // Default constructor
    }

    /**
     * Constructor to initialize a PaymentTransaction object with specified details.
     *
     * @param user              The user who made the transaction.
     * @param transactionDate   The date and time of the transaction.
     * @param transactionName   The name or description of the transaction.
     * @param fromAccountNumber The account number from which the transaction was made.
     * @param toAccountNumber   The account number to which the transaction was made.
     * @param transactionType   The type of transaction (e.g., debit, credit).
     * @param amount            The amount of money involved in the transaction.
     * @param referenceNumber   The reference number associated with the transaction.
     */
    public PaymentTransaction(User user, Date transactionDate, String transactionName,
                              String fromAccountNumber, String toAccountNumber, String transactionType,
                              Double amount, String referenceNumber) {
        this.user = user;
        this.transactionDate = transactionDate;
        this.transactionName = transactionName;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.referenceNumber = referenceNumber;
    }

    // Getters and setters for all fields

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    /**
     * Returns a string representation of the PaymentTransaction object.
     *
     * @return A string containing transaction details.
     */
    @Override
    public String toString() {
        return "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionName='" + transactionName + '\'' +
                ", fromAccountNumber='" + fromAccountNumber + '\'' +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", referenceNumber='" + referenceNumber + '\'';
    }
}

