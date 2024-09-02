package com.example.budget_bounty.model1;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the primary key
    @Column(name = "transaction_id")  // Map to the database column
    private Integer transactionId;

    @ManyToOne  // Many transactions to one user
    @JoinColumn(name = "user_id")  // Foreign key column in the database
    private User user;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_name")
    private String transactionName;

    @Column(name = "from_account_number")
    private String fromAccountNumber;

    @Column(name = "to_account_number")
    private String toAccountNumber;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "reference_number")
    private String referenceNumber;
    
    //constructors
    public PaymentTransaction() {
    	
    }
    
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
    
    @Override
    public String toString() {
        return "transactionId=" + transactionId +
                ", user=" + user +
                ", transactionDate=" + transactionDate +
                ", transactionName='" + transactionName + '\'' +
                ", fromAccountNumber='" + fromAccountNumber + '\'' +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", referenceNumber='" + referenceNumber + '\'';
    }
}
